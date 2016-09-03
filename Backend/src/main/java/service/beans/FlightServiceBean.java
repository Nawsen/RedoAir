package service.beans;

import VO.CustomerFlightVO;
import domain.Flight;
import ma.glasnost.orika.MapperFacade;
import repository.FlightRepository;
import service.FlightService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class FlightServiceBean implements FlightService{
    @Inject
    private FlightRepository flightRepo;

    @Override
    public List<Flight> findFlights(String arrivalId, String departureId){
        return flightRepo.getFlightsForAirport(arrivalId, departureId);
    }

}
