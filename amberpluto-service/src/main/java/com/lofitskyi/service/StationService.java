package com.lofitskyi.service;

import com.lofitskyi.entity.Station;

import java.util.List;

public interface StationService {

    List<Station> getAll();
    List<Station> getByCityId(Long id);
}
