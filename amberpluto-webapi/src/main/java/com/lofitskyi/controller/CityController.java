package com.lofitskyi.controller;

import com.lofitskyi.entity.City;
import com.lofitskyi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public List<City> getCity(){
        return service.getByStateId(1L);
    }

}
