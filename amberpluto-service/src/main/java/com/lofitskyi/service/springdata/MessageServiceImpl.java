package com.lofitskyi.service.springdata;

import com.lofitskyi.entity.Journey;
import com.lofitskyi.entity.Message;
import com.lofitskyi.service.MessageService;
import com.lofitskyi.service.util.Sender;

@org.springframework.stereotype.Service
public class MessageServiceImpl implements MessageService {

    private static final String EMAIL = "edosgk@gmail.com";
    private static final String PASSWORD = "vjsjed7771200";

    @Override
    public void sendToAdmin(Message message) {
        Sender sender = new Sender(EMAIL, PASSWORD);
        sender.send("From main-page form", "From: " + message.getName() + " [" + message.getFrom() + "]" + "\n" + message.getText(), message.getFrom(), EMAIL);
    }

    @Override
    public void sendToCustomerTicket(Journey targetJourney, String email, String barcode) {
        Sender sender = new Sender(EMAIL, PASSWORD);
    }
}