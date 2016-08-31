package domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private Double basePrice;
    @ManyToOne
    @ElementCollection
    private List<Discount> discounts;
    private Airport departedFrom;
    private Airport arrivalIn;

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

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
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
}
