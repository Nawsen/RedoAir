package com.realdolmen.repository;

import com.realdolmen.domain.Flight;

import java.util.List;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public interface FlightRepository extends Repository<Flight, Long>{

    List<Flight> getFlightsForAirport(String arrivalId, String departureId);


}
