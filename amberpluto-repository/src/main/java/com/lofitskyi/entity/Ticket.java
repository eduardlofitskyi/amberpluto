package com.lofitskyi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "trip_id")
    private Journey trip;

    @Column(name = "email")
    private String email;

    @Column(name = "reserved_seats")
    private int reservedSeats;

    @Column(name = "barcode")
    private String barcode;

    public Ticket() {
    }

    public Ticket(Journey trip, String email, int reservedSeats, String barcode) {
        this.trip = trip;
        this.email = email;
        this.reservedSeats = reservedSeats;
        this.barcode = barcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Journey getTrip() {
        return trip;
    }

    public void setTrip(Journey trip) {
        this.trip = trip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
