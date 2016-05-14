package com.lofitskyi.controller;

import com.lofitskyi.entity.Route;
import com.lofitskyi.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/route")
public class RouteController {

    @Autowired
    private RouteService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Route> getAllRoutes(){
        return service.getAll();
    }

    @RequestMapping(value = "/get/city/{city}", method = RequestMethod.GET)
    public List<Route> getRoutesByCity(@PathVariable long city){
        return service.getRoutesBySourceCityId(city);
    }
}
