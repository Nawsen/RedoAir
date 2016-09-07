package com.realdolmen.service;

import com.realdolmen.repository.AirportRepository;
import com.realdolmen.service.beans.AirportServiceBean;
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
public class AirportServiceTest {

    @InjectMocks
    private AirportServiceBean service;

    @Mock
    private AirportRepository repo;

    @Test
    public void testIfGetAllAirportsIsCalled() {
        service.getAllAirports();
        verify(repo, times(1)).getAllAirports();
        verifyNoMoreInteractions(repo);
    }
    @Test
    public void testIfFilteredAirportsIsCalled(){
        service.getFilteredAirports("test");
        verify(repo, times(1)).getFilteredAirports("test");
        verifyNoMoreInteractions(repo);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIfFilteredAirportsIsCalledWithNull(){
        service.getFilteredAirports(null);
        verifyNoMoreInteractions(repo);
    }
}
