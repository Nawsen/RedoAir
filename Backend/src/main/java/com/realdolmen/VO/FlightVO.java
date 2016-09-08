package com.realdolmen.VO;

import com.realdolmen.domain.Airport;

import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 1/09/2016.
 */
public class FlightVO {
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private Airport departedFrom;
    private Airport arrivalIn;

    public FlightVO() {
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airport getDepartedFrom() {
        return departedFrom;
    }

    public void setDepartedFrom(Airport departedFrom) {
        this.departedFrom = departedFrom;
    }

    public Airport getArrivalIn() {
        return arrivalIn;
    }

    public void setArrivalIn(Airport arrivalIn) {
        this.arrivalIn = arrivalIn;
    }
}
