package com.lofitskyi.controller;

import com.lofitskyi.entity.Bus;
import com.lofitskyi.entity.City;
import com.lofitskyi.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Bus> getAllBuses(){
        return service.getAll();
    }
}
