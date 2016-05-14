package com.lofitskyi.service.springdata;


import com.lofitskyi.entity.BusType;
import com.lofitskyi.repository.BusTypeRepository;
import com.lofitskyi.service.BusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusTypeServiceImpl implements BusTypeService{

    @Autowired
    private BusTypeRepository repository;

    @Override
    public List<BusType> getAll() {
        return repository.findAll();
    }

    @Override
    public List<BusType> getByBrand(String brand) {
        return null;
    }

    @Override
    public List<BusType> getByModel(String brand) {
        return null;
    }
}
