package com.lofitskyi.controller;

import com.lofitskyi.entity.Journey;
import com.lofitskyi.entity.Ticket;
import com.lofitskyi.repository.JourneyRepository;
import com.lofitskyi.service.JourneyService;
import com.lofitskyi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/journey")
public class JourneyController {

    @Autowired
    private JourneyService service;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Journey> getAllDrivers(){
        return service.getAll();
    }


    @RequestMapping(value = "/get/{source}/{destination}/{date}", method = RequestMethod.GET)
    public List<Journey> getAllDrivers(@PathVariable long source,
                                       @PathVariable long destination,
                                       @PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate date){
        System.out.println(date);
        return service.getBySourceDestDate(source,destination, date);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Journey getOne(@PathVariable long id){
        return service.getOne(id);
    }

    @RequestMapping(value = "/buy/{id}/{passengers}/{email}", method = RequestMethod.GET)
    public void buy(@PathVariable long id,
                    @PathVariable int passengers,
                    @PathVariable String email){
        service.buy(id, passengers);
        Journey current = service.getOne(id);
        String barcode = generateBarcode(current, email, passengers);
        ticketService.createOne(new Ticket(current, email, passengers, barcode));
    }

    private String generateBarcode(Journey current, String email, int passengers) {
        return current.getId()+email.substring(0,2).toUpperCase()+(System.currentTimeMillis()%100)+passengers+"F0";
    }
}
