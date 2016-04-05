package com.lofitskyi.service;


import com.lofitskyi.entity.BusType;

import java.util.List;

public interface BusTypeService {

    List<BusType> getAll();
    List<BusType> getByBrand(String brand);
    List<BusType> getByModel(String brand);
}
