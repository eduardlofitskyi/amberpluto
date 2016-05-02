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
                $('#result_table').find('tbody').append('<tr><td>' + trips[i].route.departureStation.name + ', ' + trips[i].route.departureStation.city.name + '<br>' + trips[i].route.arrivalStation.name + ', ' + trips[i].route.arrivalStation.city.name + '</td><td>' + trips[i].departureTime.hour + ':' + trips[i].departureTime.minute + '<br>' + (trips[i].departureTime.hour+2) + ':' + (trips[i].departureTime.minute+15) + '</td><td>' + minutesToHours(trips[i].route.establishedTime) + '</td><td><button class="button fit">BUY NOW!<br>$' + trips[i].route.establishedPrice*passengersNum + '</button></td></tr>');
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