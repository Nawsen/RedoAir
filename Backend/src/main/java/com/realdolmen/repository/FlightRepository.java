package com.realdolmen.repository;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;

import java.util.List;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public interface FlightRepository extends Repository<Flight, Long>{

    List<Flight> getFlightsForAirport(String arrivalId, String departureId);
    Flight getFlightFromId(Long flightId);
    Flight getFlightFromFlightNumber(Long flightNumber);
    List<Flight> findAllAvailableFlights();
}
