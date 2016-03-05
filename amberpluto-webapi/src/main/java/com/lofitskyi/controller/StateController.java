package com.lofitskyi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eduard on 3/5/16.
 */

@RestController
@RequestMapping("/state")
public class StateController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getState(){
        return "New York, NY";
    }
}
