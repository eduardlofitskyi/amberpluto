package com.lofitskyi.controller;

import com.lofitskyi.entity.City;
import com.lofitskyi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/city")
public class CityController {

    @Autowired
    private CityService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<City> getAllCities(){
        return service.getAll();
    }

    @RequestMapping(value = "/get/state/{state}", method = RequestMethod.GET)
    public List<City> getCitiesByState(@PathVariable long state){
        return service.getByStateId(state);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public City getCityById(@PathVariable long id){
        return service.getOne(id);
    }
}
