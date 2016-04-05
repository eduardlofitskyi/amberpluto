package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Driver;
import com.lofitskyi.repository.DriverRepository;
import com.lofitskyi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository repository;

    @Override
    public List<Driver> getAll() {
        return repository.findAll();
    }
}
