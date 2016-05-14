package com.lofitskyi.service;

import com.lofitskyi.entity.Journey;
import com.lofitskyi.entity.Message;

public interface MessageService {

    void sendToAdmin(Message message);
    void sendToCustomerTicket(Journey targetJourney, String email, String barcode);
}
