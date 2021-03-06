package com.realdolmen.VO;

import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.Ticket;

import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 1/09/2016.
 */
public class BookingVO {
    private List<TicketVO> tickets;
    private Long flightNumber;
    private Long creditCardNumber;
    private Integer cvc;
    private Integer expiryMonth;
    private Integer expiryYear;

    public BookingVO() {

    }

    public List<TicketVO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketVO> tickets) {
        this.tickets = tickets;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }
}
