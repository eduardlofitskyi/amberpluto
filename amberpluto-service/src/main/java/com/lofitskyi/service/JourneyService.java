package com.lofitskyi.service;

import com.lofitskyi.entity.Journey;

import java.util.List;

public interface JourneyService{

    List<Journey> getAll();
    List<Journey> getBySourceDestDate(long source, long destination, String date);
}
