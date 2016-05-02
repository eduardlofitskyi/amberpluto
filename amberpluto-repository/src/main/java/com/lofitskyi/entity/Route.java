package com.lofitskyi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "departure_id")
    private Station departureStation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "arrival_id")
    private Station arrivalStation;


    @Column(name = "price")
    private float establishedPrice;

    @Column(name = "duration")
    private int establishedTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public float getEstablishedPrice() {
        return establishedPrice;
    }

    public void setEstablishedPrice(float establishedPrice) {
        this.establishedPrice = establishedPrice;
    }

    public int getEstablishedTime() {
        return establishedTime;
    }

    public void setEstablishedTime(int establishedTime) {
        this.establishedTime = establishedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (!departureStation.equals(route.departureStation)) return false;
        return arrivalStation.equals(route.arrivalStation);

    }

    @Override
    public int hashCode() {
        int result = departureStation.hashCode();
        result = 31 * result + arrivalStation.hashCode();
        return result;
    }
}
