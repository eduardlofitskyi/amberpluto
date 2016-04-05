package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Bus;
import com.lofitskyi.repository.BusRepository;
import com.lofitskyi.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService{

    @Autowired
    private BusRepository repository;

    @Override
    public List<Bus> getAll() {
        return repository.findAll();
    }

    @Override
    public Bus getById(long id) {
        return repository.findOne(id);
    }
}
