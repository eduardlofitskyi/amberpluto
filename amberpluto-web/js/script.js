var ip = 'http://localhost:';
var port = '8080/';
var prefix = ip + port + 'rest/';

var defaultCity = '<option selected="selected" disabled>Select origin</option>';

var restGetAllStates = function () {
    $.ajax({
        type: 'GET',
        url: prefix + "state/get",
        dataType: 'json',
        async: true,
        success: function(data) {
            var states = data;
            for (var i = 0; i < states.length; i++){
                $('#state_select').append('<option value="' + states[i].id + '">' + states[i].name + ', ' + states[i].shortName +'</option>');
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
                $('#city_select').append('<option value="' + cities[i].id + '">' + cities[i].name + '</option>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })
};



var getCitiesByState = function(){
    removeCities();
    var id = $('#state_select').val();
    console.log(id);

    $.ajax({
        type: 'GET',
        url: prefix + "city/get/state/" + id,
        dataType: 'json',
        async: true,
        success: function(data) {
            var cities = data;
            for (var i = 0; i < cities.length; i++){
                $('#city_select').append('<option value="' + cities[i].id + '">' + cities[i].name + '</option>');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    })

};

var removeCities = function(){
    $('#city_select')
        .empty()
        .append(defaultCity);
    console.log('set default');
};

