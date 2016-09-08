package com.realdolmen.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findFlights", query = "SELECT f FROM Flight f WHERE f.departedFrom.code = :departure AND f.arrivalIn.code = :arrival"),
        @NamedQuery(name = "findFlightByFlightNumber", query = "SELECT f FROM Flight f WHERE f.flightNumber = :number")
})
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private String flightNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    @OneToMany(fetch = FetchType.EAGER)
    @ElementCollection
    private List<Discount> discounts;
    @OneToOne
    private Airport departedFrom;
    @OneToOne
    private Airport arrivalIn;
    @OneToMany
    @ElementCollection
    private List<Ticket> tickets;

    public Flight() {
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
