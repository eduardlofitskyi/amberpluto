package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Journey;
import com.lofitskyi.repository.JourneyRepository;
import com.lofitskyi.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService{

    @Autowired
    private JourneyRepository repository;

    @Override
    public List<Journey> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Journey> getBySourceDestDate(long source, long destination, String date) {
        return repository.getBySDD(source, destination);
    }
}
