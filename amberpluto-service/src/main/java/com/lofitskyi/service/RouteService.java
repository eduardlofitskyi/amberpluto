package com.lofitskyi.service;

import com.lofitskyi.entity.Route;

import java.util.List;

public interface RouteService{

    List<Route> getAll();
    List<Route> getRoutesBySourceCityId(Long cityId);
    List<Route> getRoutesBySourceAndDestinationCityId(Long sourceCityId, Long destinationCityId);
    boolean addRoute(Route route);
}
