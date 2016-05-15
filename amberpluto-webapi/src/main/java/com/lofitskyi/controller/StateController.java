package com.lofitskyi.controller;

import com.lofitskyi.entity.State;
import com.lofitskyi.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<State> getAllState(){
        return service.getAll();
    }
}
