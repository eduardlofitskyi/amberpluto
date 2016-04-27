package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Route;
import com.lofitskyi.repository.RouteRepository;
import com.lofitskyi.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository repository;

    @Override
    public List<Route> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Route> getRoutesBySourceCityId(Long cityId) {
        return repository.getByCityId(cityId)
                .parallelStream()
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public List<Route> getRoutesBySourceAndDestinationCityId(Long sourceCityId, Long destinationCityId) {
        return null;
    }

    @Override
    public boolean addRoute(Route route) {
        return false;
    }
}
