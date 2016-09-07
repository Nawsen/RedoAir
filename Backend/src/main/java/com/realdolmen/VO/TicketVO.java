package com.realdolmen.VO;

import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;

import java.util.List;

/**
 * Created by WVDAZ49 on 1/09/2016.
 */
public class TicketVO {
    private String firstName;
    private String lastName;
    private SeatType seatType;
    public TicketVO() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
