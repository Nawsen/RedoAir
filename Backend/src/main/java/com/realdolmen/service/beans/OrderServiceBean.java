package com.realdolmen.service.beans;

import com.realdolmen.VO.BookingVO;
import com.realdolmen.VO.TicketOrderDetailsVO;
import com.realdolmen.domain.*;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.ApplicationSettingsRepository;
import com.realdolmen.repository.BookingRepository;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.service.CustomerService;
import com.realdolmen.service.OrderService;
import ma.glasnost.orika.MapperFacade;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
public class OrderServiceBean implements OrderService {

    @Inject
    private FlightRepository flightRepo;

    @Inject
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Inject
    private BookingRepository bookingRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    @EntityMapper(type = MapperType.FLIGHT_TICKET_DETAILS)
    private MapperFacade ticketFlightMapper;

    @Inject
    @EntityMapper(type = MapperType.BOOKING_CREATE)
    private MapperFacade bookingMapper;

    @Override
    public List<TicketOrderDetailsVO> getTicketOrderDetails(Long flightId) {
        List<Ticket> t = calculateTicketPricesForAvailableTickets(flightRepo.getFlightFromId(flightId));
        return ticketFlightMapper.mapAsList(t, TicketOrderDetailsVO.class);
    }

    @Override
    public void createBooking(String email, BookingVO bookingVO) {
        Booking booking = bookingMapper.map(bookingVO, Booking.class);
        Customer customer = customerRepository.getCustomerByEmail(email);
        customer.getBookings().add(booking);
        bookingRepository.insert(booking);
        customerRepository.update(customer);
    }

    private List<Ticket> calculateTicketPricesForAvailableTickets(Flight f){
        Integer percProfit = Integer.parseInt(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE"));

        //list of all available tickets with their correct prices
        List<Ticket> freeTickets = new ArrayList<>();

        //we have to change the price for every ticket
        //so every time some1 makes a new order prices get recalculated
        for(Ticket t: f.getTickets()){
            //check if ticket is still available
            if (!t.getSold()){
                //check if an overrideprice is set
                if (t.getOverRidePrice() != null){
                    t.setSoldPrice(t.getOverRidePrice());
                } else {
                    //calculate the sell price with default settings
                    t.setSoldPrice(t.getBasePrice().add(t.getBasePrice().multiply(new BigDecimal(percProfit)).divide(new BigDecimal(100))));
                }
                //calculation is done, now add it to the feeTickets list
                freeTickets.add(t);
            }
        }
        return freeTickets;
    }

}
