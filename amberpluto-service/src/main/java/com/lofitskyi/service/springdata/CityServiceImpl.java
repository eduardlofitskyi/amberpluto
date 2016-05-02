package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.City;
import com.lofitskyi.entity.State;
import com.lofitskyi.repository.CityRepository;
import com.lofitskyi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository repository;

    @Override
    public City getOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<City> getAll() {
        return repository.findAll();
    }

    @Override
    public List<City> getByStateId(Long id) {
        return repository.getByStateId(id);
    }
}
