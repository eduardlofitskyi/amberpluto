package com.lofitskyi.controller;

import com.lofitskyi.entity.BusType;
import com.lofitskyi.service.BusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/bustype")
public class BusTypeController {

    @Autowired
    private BusTypeService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<BusType> getAllBusTypes(){
        return service.getAll();
    }
}
