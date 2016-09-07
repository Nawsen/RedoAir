package com.realdolmen.repository;

import com.realdolmen.domain.Booking;
import com.realdolmen.domain.Flight;
import com.realdolmen.repository.Repository;

import java.util.List;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public interface BookingRepository extends Repository<Booking, Long>{

    void createBooking(Booking booking);

}
