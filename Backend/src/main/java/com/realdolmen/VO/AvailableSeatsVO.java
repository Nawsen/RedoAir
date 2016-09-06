package com.realdolmen.VO;

import com.realdolmen.domain.SeatType;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
public class AvailableSeatsVO {
    private SeatType type;
    private Integer amount;

    public AvailableSeatsVO() {
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
