package com.realdolmen.service;

import com.realdolmen.VO.BookingVO;
import com.realdolmen.VO.TicketOrderDetailsVO;

import java.util.List;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
public interface OrderService {
    List<TicketOrderDetailsVO> getTicketOrderDetails(Long flightId);
    void createBooking(String email, BookingVO bookingVO);
}
