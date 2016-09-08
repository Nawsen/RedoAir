package com.realdolmen.controller;

import com.realdolmen.VO.ApplicationSettingsVO;
import com.realdolmen.VO.CustomerLoginVO;
import com.realdolmen.VO.CustomerRegisterVO;
import com.realdolmen.VO.EmployeeFlightVO;
import com.realdolmen.domain.AccountType;
import com.realdolmen.domain.ApplicationSettings;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SeatType;
import com.realdolmen.qualifiers.Auth;
import com.realdolmen.qualifiers.AuthEmp;
import com.realdolmen.service.ApplicationService;
import com.realdolmen.service.CustomerService;
import com.realdolmen.service.FlightService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("/settings")
@Stateless
public class SettingsController {

    @Inject
    private ApplicationService service;

    @Inject
    private FlightService flightService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @AuthEmp
    public ApplicationSettingsVO getAllApplicationSettings(){
        return service.getAllSettings();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @AuthEmp
    public void setAllApplicationSettings(ApplicationSettingsVO vo){
        service.setAllSettings(vo);
    }

    @GET
    @Path("/flights")
    @Produces(MediaType.APPLICATION_JSON)
    @AuthEmp
    public List<EmployeeFlightVO> getFlightsOverride(){
        return flightService.findAvailableFlights();
    }

    @POST
    @Path("/flights")
    @Consumes(MediaType.APPLICATION_JSON)
    @AuthEmp
    public Response setFlightTicketOverridePrices(EmployeeFlightVO flight){
        flightService.setFlightTicketOverridePrices(flight);
        return Response.accepted().build();
    }
}
