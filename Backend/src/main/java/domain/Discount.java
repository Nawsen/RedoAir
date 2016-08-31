package domain;

import javax.persistence.*;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer minNumberSeats;
    private Integer percentage;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinNumberSeats() {
        return minNumberSeats;
    }

    public void setMinNumberSeats(Integer minNumberSeats) {
        this.minNumberSeats = minNumberSeats;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
