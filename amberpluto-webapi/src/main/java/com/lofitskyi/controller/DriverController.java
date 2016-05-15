package com.lofitskyi.controller;

import com.lofitskyi.entity.Driver;
import com.lofitskyi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Driver> getAllDrivers(){
        return service.getAll();
    }
}
