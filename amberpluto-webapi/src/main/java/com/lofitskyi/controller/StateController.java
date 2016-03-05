package com.lofitskyi.controller;

import com.lofitskyi.entity.State;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public State getState(){
        return mockState();
    }

    private State mockState() {
        State state = new State();
        state.setId(1);
        state.setName("New York");
        state.setShortName("NY");
        return state;
    }
}
