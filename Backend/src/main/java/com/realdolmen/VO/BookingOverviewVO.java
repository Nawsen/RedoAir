package com.realdolmen.VO;

import java.util.List;

/**
 * Created by WVDAZ49 on 1/09/2016.
 */
public class BookingOverviewVO {
    private List<TicketBookingOverviewVO> tickets;
    private Long flightNumber;
    private FlightVO flight;

    public BookingOverviewVO() {

    }


    public FlightVO getFlight() {
        return flight;
    }

    public void setFlight(FlightVO flight) {
        this.flight = flight;
    }

    public List<TicketBookingOverviewVO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketBookingOverviewVO> tickets) {
        this.tickets = tickets;
    }


    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }
}
