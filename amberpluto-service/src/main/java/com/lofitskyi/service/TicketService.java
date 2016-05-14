package com.lofitskyi.service;

import com.lofitskyi.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getOne(long id);
    List<Ticket> getAll();

    void createOne(Ticket ticket);
}