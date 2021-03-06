package com.realdolmen.VO;

import com.realdolmen.domain.Airport;

import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 8/09/2016.
 */
public class EmployeeFlightVO {
    private Long flightId;
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private Airport departedFrom;
    private Airport arrivalIn;
    private List<TicketTypePriceVO> ticketType;

    public EmployeeFlightVO() {
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
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

    public List<TicketTypePriceVO> getTicketType() {
        return ticketType;
    }

    public void setTicketType(List<TicketTypePriceVO> ticketType) {
        this.ticketType = ticketType;
    }
}
