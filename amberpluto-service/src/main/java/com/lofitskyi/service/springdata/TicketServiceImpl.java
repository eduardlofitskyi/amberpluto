package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Ticket;
import com.lofitskyi.repository.TicketRepository;
import com.lofitskyi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository repository;

    @Override
    public Ticket getOne(long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Ticket> getAll() {
        return repository.findAll();
    }

    @Override
    public void createOne(Ticket ticket) {
        repository.saveAndFlush(ticket);
    }
}
