package com.lofitskyi.service;

import com.lofitskyi.entity.Bus;

import java.util.List;

public interface BusService {

    List<Bus> getAll();
    Bus getById(long id);
}
