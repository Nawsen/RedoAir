package com.realdolmen.service.beans;

import com.realdolmen.domain.Flight;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.repository.beans.FlightRepositoryBean;
import com.realdolmen.service.FlightService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class FlightServiceBean implements FlightService {
    @Inject
    private FlightRepository flightRepo;

    @Override
    public List<Flight> findFlights(String arrivalId, String departureId) {
        return flightRepo.getFlightsForAirport(arrivalId, departureId);
    }

}
