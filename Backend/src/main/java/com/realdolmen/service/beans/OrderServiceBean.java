package com.realdolmen.service.beans;

import com.realdolmen.VO.BookingVO;
import com.realdolmen.VO.TicketOrderDetailsVO;
import com.realdolmen.domain.*;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.*;
import com.realdolmen.service.CustomerService;
import com.realdolmen.service.FlightService;
import com.realdolmen.service.OrderService;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.Hibernate;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
@Stateless
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
    private TicketRepository ticketRepository;

    @Inject
    private FlightService flightService;

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
        Flight flight = flightRepo.getFlightFromFlightNumber(bookingVO.getFlightNumber());
        Customer customer = customerRepository.getCustomerByEmail(email);
        customer.getBookings().add(booking);


        if (checkIfEnoughSeatsAreAvailable(flight, booking.getTickets())) {
//            booking.getTickets().forEach(t -> t.setSold(true));
            List<Ticket> availableTickets = getAvailableTicketsFromFlightBySeatType(flight, booking.getTickets().get(0).getSeatType());

            for (int i = 0; i < booking.getTickets().size(); i++) {
                Ticket currentTicket = availableTickets.get(i);
                Person person = new Person();


                person.setLastName(bookingVO.getTickets().get(i).getLastName());
                person.setFirstName(bookingVO.getTickets().get(i).getFirstName());
                currentTicket.setPerson(person);
                currentTicket.setSold(true);
            }
            booking.getTickets().forEach(t -> ticketRepository.insert(t));
            bookingRepository.insert(booking);
            customerRepository.update(customer);
        } else {
            throw new WebApplicationException("Not enough seats available");
        }

    }

    private List<Ticket> getAvailableTicketsFromFlightBySeatType(Flight flight, SeatType seatType) {
        List<Ticket> availableTickets = new ArrayList<>();
        flight.getTickets().forEach(t -> {
            if (!t.getSold()) {
                if (t.getSeatType() == seatType) {
                    availableTickets.add(t);
                }
            }
        });
        return availableTickets;
    }
    private boolean checkIfEnoughSeatsAreAvailable(Flight flight, List<Ticket> tickets) {
        return flightService.checkForFreeTickets(flight, tickets.get(0).getSeatType(), tickets.size());
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public List<Booking> getAll(String email) {
        return customerRepository.getCustomerByEmailFetchBookings(email);
    }

    private List<Ticket> calculateTicketPricesForAvailableTickets(Flight f) {
        Integer percProfit = Integer.parseInt(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE"));

        //list of all available tickets with their correct prices
        List<Ticket> freeTickets = new ArrayList<>();

        //we have to change the price for every ticket
        //so every time some1 makes a new order prices get recalculated
        for (Ticket t : f.getTickets()) {
            //check if ticket is still available
            if (!t.getSold()) {
                //check if an overrideprice is set
                if (t.getOverRidePrice() != null) {
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
