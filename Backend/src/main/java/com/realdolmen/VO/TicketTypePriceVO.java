package com.realdolmen.VO;

import com.realdolmen.domain.SeatType;

/**
 * Created by WVDAZ49 on 8/09/2016.
 */
public class TicketTypePriceVO {
    private SeatType type;
    private Integer seats;
    private Double amount;
    private Double overrideAmount;

    public TicketTypePriceVO() {
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Double getOverrideAmount() {
        return overrideAmount;
    }

    public void setOverrideAmount(Double overrideAmount) {
        this.overrideAmount = overrideAmount;
    }
}
