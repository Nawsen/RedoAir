package com.realdolmen.VO;

import com.realdolmen.domain.Person;
import com.realdolmen.domain.SeatType;

import java.math.BigDecimal;

/**
 * Created by WVDAZ49 on 1/09/2016.
 */
public class TicketBookingOverviewVO {

    private SeatType seatType;
    private Person person;
    private Double price;

    public TicketBookingOverviewVO() {

    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
