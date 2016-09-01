package service;

import VO.CustomerFlightVO;
import domain.Flight;
import ma.glasnost.orika.MapperFacade;
import repository.FlightRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class FlightService {
    @Inject
    FlightRepository flightRepo;



    public List<Flight> findFlights(String arrivalId, String departureId){
        return flightRepo.getFlightsForAirport(arrivalId, departureId);
    }

}
