package com.lofitskyi.service;

import com.lofitskyi.entity.City;
import com.lofitskyi.entity.State;

import java.util.List;

public interface CityService {

    List<City> getAll();
    List<City> getByStateId (Long id);
}
