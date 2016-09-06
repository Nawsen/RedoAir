package com.realdolmen.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private Boolean sold;
    @Embedded
    private Person person;
    private BigDecimal basePrice;
    private BigDecimal overRidePrice;
    private BigDecimal purchasePrice;
    private BigDecimal soldPrice;

    public Ticket() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BigDecimal getOverRidePrice() {
        return overRidePrice;
    }

    public void setOverRidePrice(BigDecimal overRidePrice) {
        this.overRidePrice = overRidePrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}
