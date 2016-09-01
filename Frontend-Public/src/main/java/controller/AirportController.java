package controller;

import domain.Airport;
import service.AirportService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("/airport")
@Stateless
public class AirportController {

    @Inject
    AirportService service;

    @GET
    @Path("/all")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Airport> getStringTest() {
        return service.getAllAirports();
    }


}
