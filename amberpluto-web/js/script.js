var ip = 'http://localhost:';
var port = '8080/';
var prefix = ip + port + 'rest/';

var defaultCityDeparture = '<option selected="selected" disabled>Select origin</option>';
var defaultCityArrival = '<option selected="selected" disabled>Select destination</option>';

var restGetAllStates = function () {
    $.ajax({
        type: 'GET',
        url: prefix + "state/get",
        dataType: 'json',
        async: true,
        success: function(data) {
            var states = data;
            for (var i = 0; i < states.length; i++){
                $('#state_select').append('<option value="' + states[i].id + '">' + states[i].name + '</option>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })
};

var restGetAllCities = function () {
    $.ajax({
        type: 'GET',
        url: prefix + "city/get",
        dataType: 'json',
        async: true,
        success: function(data) {
            var cities = data;
            for (var i = 0; i < cities.length; i++){
                $('#city_select').append('<option value="' + cities[i].id + '">' + cities[i].name + ', ' + cities[i].state.shortName + '</option>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })
};

var getCitiesByState = function(){
    removeCitiesDeparture();
    var id = $('#state_select').val();

    $.ajax({
        type: 'GET',
        url: prefix + "city/get/state/" + id,
        dataType: 'json',
        async: true,
        success: function(data) {
            var cities = data;
            for (var i = 0; i < cities.length; i++){
                $('#city_select').append('<option value="' + cities[i].id + '">' + cities[i].name + ', ' + cities[i].state.shortName + '</option>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })

};

var getDestinationCityByOrigin = function (){
    removeCitiesArrival();
    var id = $('#city_select').val();

    $.ajax({
        type: 'GET',
        url: prefix + "route/get/city/" + id,
        dataType: 'json',
        async: true,
        success: function(data) {
            var routes = data;
            var city;
            for (var i = 0; i < routes.length; i++){
                city = routes[i].arrivalStation.city;
                $('#city_select_arrival').append('<option value="' + city.id + '">' + city.name + ', ' + city.state.shortName + '</option>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })

    $('#city_select_arrival').prop('disabled', false);
};

var removeCitiesDeparture = function(){
    $('#city_select')
        .empty()
        .append(defaultCityDeparture);
};

var removeCitiesArrival = function(){
    $('#city_select_arrival')
        .empty()
        .append(defaultCityArrival);
};

var enableDepartureDate = function() {
    $('#datepicker_departure').prop('disabled', false);
};

var enablePassengersNumber = function(){
    $('#passengers_number').prop('disabled', false);
};

var enableSubmitButton = function(){
    $('#submit_button').prop('disabled', false);
};

$('#passengers_number').keyup(function () {
    this.value = this.value.replace(/[^0-9]/g,'');
});

$(document).ready(function() {
    $( "#datepicker_departure" ).datepicker({
        dateFormat: 'dd-mm-yy',
        minDate: '0',
        maxDate: '+60D'
    });
});

var showDate = function() {
    var str = $('#datepicker_departure').val();
    str = str.replace(/\//g, '-');
    console.log(str);
};

var sendMessage = function () {

    var mailStructure = {
        'name': $('#name_message').val(),
        'from': $('#email_message').val(),
        'text': $('#text_message').val()
    };

        $.ajax({
            type: 'POST',
            url: prefix + "email/question",
            dataType: 'json',
            async: true,
            data: JSON.stringify(mailStructure),
            contentType: 'application/json; charset=utf-8',
            success: function() {
                console.log("successful");
                alert("Your message has been sent successfully!");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("error");
                alert("Your message has been sent successfully!");
            }
        })
};

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
        return null;
    }
    else{
        return results[1] || 0;
    }
};

var searchTripByMainParams = function () {
    var originId = $.urlParam('origin');
    var destinationId = $.urlParam('destination');
    var passengersNum = $.urlParam('passengers');
    var departureDate = $.urlParam('date');
    //var isExactDate = $.urlParam('exact_date');

    setGeneralLabel(originId, destinationId, passengersNum, departureDate);

    setGeneralTable(originId, destinationId, passengersNum, departureDate);
};
function setGeneralLabel(originId, destinationId, passengersNum, departureDate) {
    var originStr;
    var destStr;

    function getCityById(id) {
        return $.ajax({
            type: 'GET',
            url: prefix + "city/get/" + id,
            dataType: 'json',
            async: false,
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error occur");
            }
        })
    }

    setSearchAttrLabel(originId, destinationId, passengersNum, departureDate);

    function setSearchAttrLabel(originId, destinationId, passengersNum, departureDate) {
        departureDate = departureDate.replace(/%2F/g, '/');
        var originResponse = getCityById(originId).responseText;
        var destResponse = getCityById(destinationId).responseText;
        originResponse = JSON.parse(originResponse);
        destResponse= JSON.parse(destResponse);
        originStr = originResponse.name + ', ' + originResponse.state.shortName;
        destStr = destResponse.name + ', ' + destResponse.state.shortName;

        $('#search_label').append('<h3>' + 'You searched for trips leaving ' + originStr + ' on ' + departureDate + ' to ' + destStr + ' for ' + passengersNum + ' passenger(s).' + '</h3>');
    }
}

function setGeneralTable(originId, destinationId, passengersNum, departureDate) {
    departureDate = departureDate.replace(/%2F/g, '-')

    $.ajax({
        type: 'GET',
        url: prefix + "/journey/get/" + originId + "/" + destinationId + "/" + departureDate,
        dataType: 'json',
        async: true,
        success: function(data) {
            console.log(data);
            var trips = data;
            for (var i = 0; i < trips.length; i++){
                console.log(trips[i].route.departureStation.name);
                $('#result_table').find('tbody').append('<tr><td><b>A: </b>' + trips[i].route.departureStation.name + ', ' + trips[i].route.departureStation.city.name + '<br><b>B: </b>' + trips[i].route.arrivalStation.name + ', ' + trips[i].route.arrivalStation.city.name + '</td><td>' + trips[i].departureTime.hour + ':' + trips[i].departureTime.minute + '<br>' + (trips[i].departureTime.hour+2) + ':' + (trips[i].departureTime.minute+15) + '</td><td>' + minutesToHours(trips[i].route.establishedTime) + '</td><td><a href="details.html?id=' + trips[i].id + '&passengers=' + passengersNum + '" class="button fit">BUY NOW!<br>$' + trips[i].route.establishedPrice*passengersNum + '</a></td></tr>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })

}

var minutesToHours = function (minutes) {
    return Math.floor(minutes / 60) + ':' + minutes % 60;
};

var showDetailOnTrip = function () {
    var tripId = $.urlParam('id');
    var passengers = $.urlParam('passengers');

    var tripJSON = $.ajax({
        type: 'GET',
        url: prefix + "/journey/get/" + tripId,
        dataType: 'json',
        async: true,
        success: function(data) {
            console.log(data);

            initMap(data);
            setDetailLabel(data, passengers);
            setDatailsButton(tripId, passengers);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
};
function setDetailLabel(trip, passengers) {
    var depMin = "00";
    var arrMin = "00";
    if (trip.departureTime.minute != 0) depMin=trip.departureTime.minute;
    if (trip.departureTime.minute != 0) arrMin=trip.departureTime.minute;
    $('#a_point').append('<b>A:</b> ' + trip.route.departureStation.name + '. ' + trip.route.departureStation.city.name + ', ' + trip.route.departureStation.city.state.shortName + '<br>');
    $('#a_point').append(trip.departureTime.hour + ':' + depMin + ' - ' + trip.departureTime.month + ' ' + trip.departureTime.dayOfMonth + ', ' + trip.departureTime.year);
    $('#b_point').append('<b>B:</b> ' + trip.route.arrivalStation.name + '. ' + trip.route.arrivalStation.city.name + ', ' + trip.route.arrivalStation.city.state.shortName + '<br>');
    $('#b_point').append((trip.departureTime.hour + 3)+ ':' + arrMin + ' - ' + trip.departureTime.month + ' ' + trip.departureTime.dayOfMonth + ', ' + trip.departureTime.year);
    $('#sum_point').append('PASSENGER(S): ' + passengers + '<br>');
    $('#sum_point').append('PRICE: $' + (passengers * trip.route.establishedPrice));
}


function initMap(trip) {
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 2,
        //center: {lat: 41.85, lng: -87.65},
        disableDefaultUI: true,
        mapTypeId: google.maps.MapTypeId.HYBRID,
        scrollwheel: false,
        draggable: false,
        styles: [{
            "featureType": "road",
            "elementType": "road",
            "stylers": [{
                "visibility": "off"
            }]
        }, {
            "featureType": "administrative.locality",
            "elementType": "labels",
            "stylers": [{
                "visibility": "off"
            }]
        }, {}]
    });
    directionsDisplay.setMap(map);

    calculateAndDisplayRoute(directionsService, directionsDisplay, trip.route.departureStation, trip.route.arrivalStation);

}

function calculateAndDisplayRoute(directionsService, directionsDisplay, departure, arrival) {
    directionsService.route({
        origin: departure.name + ', ' + departure.city.name + ' ' + departure.city.state.shortName,
        destination: arrival.name + ', ' + arrival.city.name + ' ' + arrival.city.state.shortName,
        travelMode: google.maps.TravelMode.DRIVING
    }, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}

var checkCarNumber = function () {
    console.log("In");
    if ($('#input_card_num').val().length == 16){
        $('#pay_submit').prop('disabled', false);
        $('#card_num_err_message').text('Correct!');
    }else {
        $('#pay_submit').prop('disabled', true);
        $('#card_num_err_message').text('Input correct card number');
    }
};

var setSpinner = function () {
    $('#main').empty();
    $('#main').append('<div class="loader">Loading...</div>');
};

var setDatailsButton = function (id, passengers) {
    $('#floating-panel').append('<a href="purchase.html?id=' + id + '&passengers=' + passengers + '" class="button special big icon fa-paypal">BUY NOW</a>')
};

var buy = function () {
    var tripId = $.urlParam('id');
    var passengers = $.urlParam('passengers');

    $.ajax({
        type: 'GET',
        url: prefix + "/journey/buy/" + tripId + "/" + passengers,
        dataType: 'json',
        async: true,
        success: function(data) {
            console.log("Success");
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
};