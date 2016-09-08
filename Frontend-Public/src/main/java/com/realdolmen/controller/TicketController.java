package com.realdolmen.controller;

import com.realdolmen.VO.TicketOrderDetailsVO;
import com.realdolmen.domain.AccountType;
import com.realdolmen.qualifiers.Auth;
import com.realdolmen.service.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.Order;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
@Path("order")
@Stateless
public class TicketController {

    @Inject
    private OrderService service;

    @GET
    @Path("all")
    @Auth
    @Produces(value = MediaType.TEXT_HTML)
    public String getOrdersFromCustomer(@HeaderParam("email") String email) {
        return "OK";
    }

    @GET
    @Path("details")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<TicketOrderDetailsVO> getflightDetails(@QueryParam("flightId") Long id){
        return service.getTicketOrderDetails(id);
    }
}
