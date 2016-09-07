package com.realdolmen.controller;


import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SeatType;
import com.realdolmen.service.FlightService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
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
    @Path("findFilterd")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<CustomerFlightVO> findFlights(@QueryParam("departure") String departureCode,
                                              @QueryParam("arrival") String arrivalCode,
                                              @QueryParam("startDate") Double startDate,
                                              @QueryParam("endDate") Double endDate,
                                              @QueryParam("typeSeat")SeatType type,
                                              @QueryParam("placesLeft") Integer free
                                              ){
        return service.findFlights(departureCode, arrivalCode, new Date((long) (startDate*1000)), new Date((long) (endDate*1000)), type, free);
    }

    @GET
    @Path("find")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<CustomerFlightVO> findFlights(@QueryParam("departure") String departureCode,
                                              @QueryParam("arrival") String arrivalCode){
        return service.findFlights(departureCode, arrivalCode);
    }
}
