package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Station;
import com.lofitskyi.repository.StationRepository;
import com.lofitskyi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationRepository repository;

    @Override
    public List<Station> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Station> getByCityId(Long id) {
        return repository.getByCityId(id);
    }
}
