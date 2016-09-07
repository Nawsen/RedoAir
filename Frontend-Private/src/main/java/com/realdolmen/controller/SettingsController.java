package com.realdolmen.controller;

import com.realdolmen.VO.ApplicationSettingsVO;
import com.realdolmen.VO.CustomerLoginVO;
import com.realdolmen.VO.CustomerRegisterVO;
import com.realdolmen.domain.ApplicationSettings;
import com.realdolmen.domain.SeatType;
import com.realdolmen.qualifiers.Auth;
import com.realdolmen.service.ApplicationService;
import com.realdolmen.service.CustomerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Path("/settings")
@Stateless
public class SettingsController {

    @Inject
    private ApplicationService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Auth
    public ApplicationSettingsVO getAllApplicationSettings(){
        return service.getAllSettings();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Auth
    public void setAllApplicationSettings(ApplicationSettingsVO vo){
        service.setAllSettings(vo);
    }
}
