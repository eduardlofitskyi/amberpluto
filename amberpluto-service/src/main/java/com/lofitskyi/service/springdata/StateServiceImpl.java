package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.State;
import com.lofitskyi.repository.StateRepository;
import com.lofitskyi.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService{

    @Autowired
    private StateRepository repository;


    @Override
    public List<State> getAll() {
        return repository.findAll();
    }
}
