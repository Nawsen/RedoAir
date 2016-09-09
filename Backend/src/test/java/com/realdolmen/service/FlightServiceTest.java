package com.realdolmen.service;

import com.realdolmen.domain.SeatType;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.service.beans.FlightServiceBean;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Test
    public void checkIfFindFlightsIsCalled(){
        service.findFlights("TestString", "TestString", new java.util.Date((long)1472774400*1000), new java.util.Date((long)1473033600*1000), SeatType.BUSINESS, 2);
        verify(repo, times(1)).getFlightsForAirport("TestString", "TestString");
        verifyNoMoreInteractions(repo);

    }
}
