package com.lofitskyi.controller;

import com.lofitskyi.entity.Station;
import com.lofitskyi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Station> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/get/city", method = RequestMethod.GET)
    public List<Station> getByCity(){
        return service.getByCityId(1L);
    }
}
