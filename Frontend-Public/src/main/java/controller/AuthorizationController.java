package controller;

import domain.Customer;
import domain.Flight;
import service.CustomerService;
import service.FlightService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("/customer")
@Stateless
public class AuthorizationController {

    @Inject
    CustomerService service;

    @POST
    @Path("/create")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public void createCustomer(Customer customer) {
        service.createCustomer(customer);
    }

    @POST
    @Path("/login")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response login(Customer customer) {
        if (service.login(customer)) {
            return Response.ok("JWT HERE").build();
        } else {
            return Response.status(418).build();
        }
    }





//    public List<Flight> findFlights(@QueryParam("departure") String departureCode, @QueryParam("arrival") String arrivalCode){
//        return service.findFlights(departureCode, arrivalCode);
//    }



}
