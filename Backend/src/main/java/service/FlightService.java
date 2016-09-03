package service;

import domain.Flight;

import java.util.List;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
public interface FlightService {
    List<Flight> findFlights(String arrivalId, String departureId);
}
