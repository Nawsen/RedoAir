package controller;

import domain.Flight;
import service.FlightService;
import service.beans.FlightServiceBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("flight")
@Stateless
public class FlightController {

    @Inject
    private FlightService service;

    @GET
    @Path("find")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Flight> findFlights(@QueryParam("departure") String departureCode, @QueryParam("arrival") String arrivalCode){
        return service.findFlights(departureCode, arrivalCode);
    }
}
