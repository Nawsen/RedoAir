package com.realdolmen.service;

import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.VO.EmployeeFlightVO;
import com.realdolmen.VO.FlightVO;
import com.realdolmen.VO.TicketTypePriceVO;
import com.realdolmen.domain.Airport;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.service.beans.FlightServiceBean;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by WVDAZ49 on 6/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    @InjectMocks
    private FlightServiceBean service;

    @Mock
    private FlightRepository repo;

    @Mock
    private MapperFacade customerFlightMapper;

    @Mock
    private MapperFacade availableFlightMapper;

    private List<Flight> flightList;

    private EmployeeFlightVO employeeFlightVO;

    @Before
    public void setup(){
        flightList = new ArrayList<>();
        Flight f = new Flight();
        f.setDepartureTime(new java.util.Date((long)1572774400*1000));
        List<Ticket> tickets = new ArrayList<>();
        Ticket t = new Ticket();
        t.setSeatType(SeatType.BUSINESS);
        t.setSold(false);
        Ticket t2 = new Ticket();
        t2.setSeatType(SeatType.BUSINESS);
        t2.setSold(false);
        Ticket t3 = new Ticket();
        t3.setSeatType(SeatType.FIRST_CLASS);
        Ticket t4 = new Ticket();
        t4.setSeatType(SeatType.ECONOMY);
        t4.setSold(false);
        tickets.add(t);
        tickets.add(t2);
        tickets.add(t3);
        tickets.add(t4);
        f.setTickets(tickets);
        flightList.add(f);

        Flight f2 = new Flight();
        f2.setDepartureTime(new java.util.Date((long)1572774400*1000));
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
        f3.setDepartureTime(new java.util.Date((long)1572774400*1000));
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

        when(repo.getFlightsForAirport("test2", "test1")).thenReturn(flightList);

        employeeFlightVO = new EmployeeFlightVO();
        employeeFlightVO.setFlightId(202L);
        List<TicketTypePriceVO> ticketTypePriceVOs = new ArrayList<>();
        TicketTypePriceVO ticketTypePriceVO = new TicketTypePriceVO();
        ticketTypePriceVO.setType(SeatType.BUSINESS);
        ticketTypePriceVO.setOverrideAmount(80D);
        TicketTypePriceVO ticketTypePriceVO2 = new TicketTypePriceVO();
        ticketTypePriceVO2.setType(SeatType.FIRST_CLASS);
        ticketTypePriceVO2.setOverrideAmount(100D);
        TicketTypePriceVO ticketTypePriceVO3 = new TicketTypePriceVO();
        ticketTypePriceVO3.setType(SeatType.ECONOMY);
        ticketTypePriceVO3.setOverrideAmount(null);
        ticketTypePriceVOs.add(ticketTypePriceVO);
        ticketTypePriceVOs.add(ticketTypePriceVO2);
        ticketTypePriceVOs.add(ticketTypePriceVO3);
        employeeFlightVO.setFlightNumber("FLIGHTNUMMER");
        employeeFlightVO.setTicketType(ticketTypePriceVOs);

    }

    @Test
    public void checkIfFindFlightsIsCalled(){
        service.findFlights("TestString", "TestString", new java.util.Date((long)1472774400*1000), new java.util.Date((long)1473033600*1000), SeatType.BUSINESS, 2);
        verify(repo, times(1)).getFlightsForAirport("TestString", "TestString");
        verifyNoMoreInteractions(repo);

    }

    @Test
    public void testReturnedFlightsFromFilterBuz(){
        List<Flight> filterdflights = new ArrayList<>();
        filterdflights.add(flightList.get(0));
        List<CustomerFlightVO> filterdvo = new ArrayList<>();
        CustomerFlightVO cvo =new CustomerFlightVO();
        filterdvo.add(cvo);
        when(customerFlightMapper.mapAsList(filterdflights, CustomerFlightVO.class)).thenReturn(filterdvo);

        List<CustomerFlightVO> flights = service.findFlights("test1", "test2", new java.util.Date((long)1472774400*1000),  new java.util.Date((long)1672774400*1000), SeatType.BUSINESS, 2);
        assertEquals(1, flights.size());
    }

    @Test
    public void testReturnedFlightsFromFilterFirst(){
        List<Flight> filterdflights = new ArrayList<>();
        filterdflights.add(flightList.get(0));
        filterdflights.add(flightList.get(1));
        List<CustomerFlightVO> filterdvo = new ArrayList<>();
        CustomerFlightVO cvo =new CustomerFlightVO();
        CustomerFlightVO cvo2 =new CustomerFlightVO();
        filterdvo.add(cvo);
        filterdvo.add(cvo2);
        when(customerFlightMapper.mapAsList(filterdflights, CustomerFlightVO.class)).thenReturn(filterdvo);

        List<CustomerFlightVO> flights = service.findFlights("test1", "test2", new java.util.Date((long)1472774400*1000),  new java.util.Date((long)1672774400*1000), SeatType.FIRST_CLASS, 1);
        assertEquals(2, flights.size());
    }

    @Test
    public void testReturnedFlightsFromFilterECO(){
        List<Flight> filterdflights = new ArrayList<>();
        filterdflights.add(flightList.get(2));
        List<CustomerFlightVO> filterdvo = new ArrayList<>();
        CustomerFlightVO cvo =new CustomerFlightVO();
        filterdvo.add(cvo);
        when(customerFlightMapper.mapAsList(filterdflights, CustomerFlightVO.class)).thenReturn(filterdvo);

        List<CustomerFlightVO> flights = service.findFlights("test1", "test2", new java.util.Date((long)1472774400*1000),  new java.util.Date((long)1672774400*1000), SeatType.ECONOMY, 3);
        assertEquals(1, flights.size());
    }
    @Test
    public void testGetAvailableFlightsIsCalled(){
        service.findAvailableFlights();
        verify(repo, times(1)).findAllAvailableFlights();
        verifyNoMoreInteractions(repo);
    }
    @Test
    public void overrideTicketPrices(){
        when(repo.getFlightFromId(202L)).thenReturn(flightList.get(0));
        service.setFlightTicketOverridePrices(employeeFlightVO);

        assertEquals(BigDecimal.valueOf(80.0), flightList.get(0).getTickets().get(0).getOverRidePrice());
        assertEquals(BigDecimal.valueOf(100.0), flightList.get(0).getTickets().get(2).getOverRidePrice());
        assertEquals(null, flightList.get(0).getTickets().get(3).getOverRidePrice());

    }
}
