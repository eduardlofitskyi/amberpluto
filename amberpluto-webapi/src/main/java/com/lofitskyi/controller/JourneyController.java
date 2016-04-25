package com.lofitskyi.controller;

import com.lofitskyi.entity.Journey;
import com.lofitskyi.repository.JourneyRepository;
import com.lofitskyi.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/journey")
public class JourneyController {

    @Autowired
    private JourneyService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Journey> getAllDrivers(){
        return service.getAll();
    }


    @RequestMapping(value = "/get/{source}/{destination}/{date}", method = RequestMethod.GET)
    public List<Journey> getAllDrivers(@PathVariable long source,
                                       @PathVariable long destination,
                                       @PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate date){
        System.out.println(date);
        return service.getBySourceDestDate(source,destination, date);
    }
}
