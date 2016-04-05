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