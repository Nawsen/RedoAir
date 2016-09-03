package controller;

import domain.Customer;
import service.CustomerService;
import service.beans.CustomerServiceBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("/customer")
@Stateless
public class AuthorizationController {

    @Inject
    private CustomerService service;

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
        String JWT = service.login(customer);
        if (JWT != null) {
            return Response.ok(JWT).build();
        } else {
            return Response.status(418).build();
        }
    }





//    public List<Flight> findFlights(@QueryParam("departure") String departureCode, @QueryParam("arrival") String arrivalCode){
//        return service.findFlights(departureCode, arrivalCode);
//    }



}
