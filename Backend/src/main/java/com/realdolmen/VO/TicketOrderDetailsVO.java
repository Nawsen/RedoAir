package com.realdolmen.VO;

import com.realdolmen.domain.SeatType;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
public class TicketOrderDetailsVO {
    private SeatType seatType;
    private Double price;

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
