package controller;

import domain.Airport;
import domain.Flight;
import service.AirportService;
import service.FlightService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("flight")
@Stateless
public class FlightController {

    @Inject
    FlightService service;

    @GET
    @Path("find")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Flight> findFlights(@QueryParam("departure") Long departureId, @QueryParam("arrival") Long arrivalId){
        return service.findFlights(departureId, arrivalId);
    }
}
