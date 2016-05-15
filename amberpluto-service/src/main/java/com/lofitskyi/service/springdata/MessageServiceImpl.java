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
        String text = "Congratulation!\n" +
                "You have bought ticket(s) on amberpluto bus service\nDeparture from: " +
                targetJourney.getRoute().getDepartureStation().getName() +
                " - " + targetJourney.getRoute().getDepartureStation().getCity().getName() +
                ", " + targetJourney.getRoute().getDepartureStation().getCity().getState().getShortName() + "\n\t" +
                targetJourney.getDepartureTime().toString() + "\n" +
                "Arriving to: " + targetJourney.getRoute().getArrivalStation().getName() +
                " - " + targetJourney.getRoute().getArrivalStation().getCity().getName() +
                ", " + targetJourney.getRoute().getArrivalStation().getCity().getState().getShortName() + "\n\t" +
                targetJourney.getDepartureTime().plusMinutes(targetJourney.getRoute().getEstablishedTime()) + "\n" +
                "Bus: " + targetJourney.getBus().getType().getBrand() + " " +targetJourney.getBus().getType().getModel() +
                ", Plate [" + targetJourney.getBus().getLicensePlate() + "]" + "\n" +
                "Driver: " + targetJourney.getDriver().getFirstName() + " " +targetJourney.getDriver().getLastName() +
                "\n\n\t\t" + barcode.toUpperCase();


        Sender sender = new Sender(EMAIL, PASSWORD);
        sender.send("Amberpluto Support", text, "support@amberpluto.com", email);
    }
}