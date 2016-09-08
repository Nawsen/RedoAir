package com.realdolmen.controller;

import com.realdolmen.VO.BookingVO;
import com.realdolmen.domain.Booking;
import com.realdolmen.qualifiers.Auth;
import com.realdolmen.service.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("/booking")
@Stateless
public class BookingController {

    @Inject
    private OrderService service;

    @POST

    @Path("/order")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Auth
    public void createNewOrder(@HeaderParam("email") String email, BookingVO booking) {
        service.createBooking(email, booking);
    }

    @GET
    @Path("/all")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Booking> getAllOrders(@HeaderParam("email") String email){
        return service.getAll(email);
    }
//    @GET
//    @Path("/all/{filter}")
//    @Produces(value = MediaType.APPLICATION_JSON)
//    public List<Airport> getFilteredAirports(@PathParam("filter") String filter) {
//        return service.getFilteredAirports(filter);
//    }


}
