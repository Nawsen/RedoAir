package com.realdolmen.controller;

import javax.ejb.Stateless;
import javax.persistence.criteria.Order;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
@Path("ticket")
@Stateless
public class TicketController {

    @Path("orders")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Order> getOrdersFromCustomer(Long customerId) {
        return null;
    }
}
