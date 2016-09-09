package com.realdolmen.service;

import com.realdolmen.VO.BookingVO;
import com.realdolmen.VO.TicketOrderDetailsVO;
import com.realdolmen.VO.TicketVO;
import com.realdolmen.domain.*;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.*;
import com.realdolmen.service.beans.OrderServiceBean;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by WVDAZ49 on 9/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceBean service;

    @Mock
    private FlightRepository flightRepo;

    @Mock
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private FlightService flightService;

    @Mock
    private MapperFacade ticketFlightMapper;

    @Mock
    private MapperFacade ticketBookingOverviewMapper;

    @Mock
    private MapperFacade bookingMapper;

    @Mock
    private MapperFacade flightBookingMapper;

    @Mock
    private MapperFacade bookingOverviewMapper;

    private List<Flight> flightList;

    private List<TicketOrderDetailsVO> ticketOrderDetailsVOs;

    private List<Flight> updatedFlight;

    private BookingVO bookingVo;

    private Booking booking;

    @Before
    public void setup() {
        flightList = new ArrayList<>();
        Flight f = new Flight();
        f.setDepartureTime(new java.util.Date((long) 1572774400 * 1000));
        List<Ticket> tickets = new ArrayList<>();
        Ticket t = new Ticket();
        t.setSeatType(SeatType.BUSINESS);
        t.setBasePrice(BigDecimal.valueOf(50));
        t.setSold(false);
        Ticket t2 = new Ticket();
        t2.setBasePrice(BigDecimal.valueOf(50));
        t2.setSeatType(SeatType.BUSINESS);
        t2.setSold(false);
        Ticket t3 = new Ticket();
        t3.setBasePrice(BigDecimal.valueOf(50));
        t3.setSeatType(SeatType.FIRST_CLASS);
        t3.setSold(false);
        Ticket t4 = new Ticket();
        t4.setSeatType(SeatType.ECONOMY);
        t4.setBasePrice(BigDecimal.valueOf(50));
        t4.setSold(false);
        tickets.add(t);
        tickets.add(t2);
        tickets.add(t3);
        tickets.add(t4);
        f.setTickets(tickets);
        flightList.add(f);

        Flight f2 = new Flight();
        f2.setDepartureTime(new java.util.Date((long) 1572774400 * 1000));
        List<Ticket> tickets2 = new ArrayList<>();
        Ticket tt = new Ticket();
        tt.setSeatType(SeatType.FIRST_CLASS);
        tt.setSold(false);
        Ticket tt2 = new Ticket();
        tt2.setSeatType(SeatType.BUSINESS);
        tt2.setSold(false);
        Ticket tt3 = new Ticket();
        tt3.setSeatType(SeatType.FIRST_CLASS);
        tt3.setSold(false);
        tickets2.add(tt);
        tickets2.add(tt2);
        tickets2.add(tt3);
        f2.setTickets(tickets2);
        flightList.add(f2);

        Flight f3 = new Flight();
        f3.setDepartureTime(new java.util.Date((long) 1572774400 * 1000));
        List<Ticket> tickets3 = new ArrayList<>();
        Ticket ttt = new Ticket();
        ttt.setSeatType(SeatType.ECONOMY);
        ttt.setSold(false);
        Ticket ttt2 = new Ticket();
        ttt2.setSeatType(SeatType.ECONOMY);
        ttt2.setSold(false);
        Ticket ttt3 = new Ticket();
        ttt3.setSeatType(SeatType.ECONOMY);
        ttt3.setSold(false);
        tickets3.add(ttt);
        tickets3.add(ttt2);
        tickets3.add(ttt3);
        f3.setTickets(tickets3);
        flightList.add(f3);

        ticketOrderDetailsVOs = new ArrayList<>();
        TicketOrderDetailsVO vo1 = new TicketOrderDetailsVO();
        vo1.setSeatType(SeatType.BUSINESS);
        vo1.setPrice(55D);
        TicketOrderDetailsVO vo2 = new TicketOrderDetailsVO();
        vo2.setSeatType(SeatType.BUSINESS);
        vo2.setPrice(55D);
        TicketOrderDetailsVO vo3 = new TicketOrderDetailsVO();
        vo3.setSeatType(SeatType.BUSINESS);
        vo3.setPrice(55D);
        TicketOrderDetailsVO vo4 = new TicketOrderDetailsVO();
        vo4.setSeatType(SeatType.BUSINESS);
        vo4.setPrice(55D);
        ticketOrderDetailsVOs.add(vo1);
        ticketOrderDetailsVOs.add(vo2);
        ticketOrderDetailsVOs.add(vo3);
        ticketOrderDetailsVOs.add(vo4);


        bookingVo = new BookingVO();
        bookingVo.setFlightNumber(123L);
        List<TicketVO> tvos = new ArrayList<>();
        TicketVO tvo1 = new TicketVO();
        TicketVO tvo2 = new TicketVO();
        TicketVO tvo3 = new TicketVO();
        tvo1.setSeatType(SeatType.ECONOMY);
        tvo2.setSeatType(SeatType.ECONOMY);
        tvo3.setSeatType(SeatType.ECONOMY);
        tvos.add(tvo1);
        tvos.add(tvo2);
        tvos.add(tvo3);
        bookingVo.setTickets(tvos);

        booking = new Booking();
        Flight flight = new Flight();
        flight.setFlightId(123L);
        booking.setFlight(flight);
        booking.setTickets(tickets);
    }

    @Test
    public void testgetTicketOrderDetails() {
        when(flightRepo.getFlightFromId(1L)).thenReturn(flightList.get(0));
        when(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE")).thenReturn("10");
        when(ticketFlightMapper.mapAsList(flightList.get(0).getTickets(), TicketOrderDetailsVO.class)).thenReturn(ticketOrderDetailsVOs);

        service.getTicketOrderDetails(1L);
        verify(flightRepo, times(1)).getFlightFromId(1L);
    }

    @Test
    public void testgetTicketOrderDetailsCorrectPrice() {
        when(flightRepo.getFlightFromId(1L)).thenReturn(flightList.get(0));
        when(applicationSettingsRepository.findValue("PROFIT_PERCENTAGE")).thenReturn("10");
        when(ticketFlightMapper.mapAsList(updateTestFlights(), TicketOrderDetailsVO.class)).thenReturn(ticketOrderDetailsVOs);
        List<TicketOrderDetailsVO> list = service.getTicketOrderDetails(1L);
        verify(flightRepo, times(1)).getFlightFromId(1L);
        assertEquals(new Double(55.0), list.get(0).getPrice());
    }

    private List<Ticket> updateTestFlights() {
        flightList.get(0).getTickets().get(0).setSoldPrice(BigDecimal.valueOf(55));
        flightList.get(0).getTickets().get(1).setSoldPrice(BigDecimal.valueOf(55));
        flightList.get(0).getTickets().get(2).setSoldPrice(BigDecimal.valueOf(55));
        flightList.get(0).getTickets().get(3).setSoldPrice(BigDecimal.valueOf(55));
        return flightList.get(0).getTickets();
    }


}
