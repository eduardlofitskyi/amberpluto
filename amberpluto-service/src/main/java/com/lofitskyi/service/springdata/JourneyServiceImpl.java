package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Journey;
import com.lofitskyi.repository.JourneyRepository;
import com.lofitskyi.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JourneyServiceImpl implements JourneyService{

    @Autowired
    private JourneyRepository repository;

    @Override
    public List<Journey> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Journey> getBySourceDestDate(long source, long destination, LocalDate date) {
        return repository.getBySDD(source, destination)
                .parallelStream()
                .filter(j -> withinDay(date, j.getDepartureTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Journey getOne(long id) {
        return repository.findOne(id);
    }

    public boolean withinDay(LocalDate expectedDate, LocalDateTime actualTime){
        if (expectedDate == null || actualTime == null) return false;

        return expectedDate.getDayOfMonth() == actualTime.getDayOfMonth()
                && expectedDate.getMonth() == actualTime.getMonth()
                && expectedDate.getYear() == actualTime.getYear();
    }
}
