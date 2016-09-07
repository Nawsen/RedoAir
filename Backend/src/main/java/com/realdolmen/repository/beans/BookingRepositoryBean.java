package com.realdolmen.repository.beans;

import com.realdolmen.domain.Booking;
import com.realdolmen.repository.BookingRepository;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */

@Stateless
public class BookingRepositoryBean extends AbstractBaseRepository<Booking, Long> implements BookingRepository {

    @Override
    public void createBooking(Booking booking) {
        insert(booking);
    }
}
