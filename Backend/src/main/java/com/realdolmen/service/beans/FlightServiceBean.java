package com.realdolmen.service.beans;

import com.realdolmen.VO.CustomerFlightVO;
import com.realdolmen.domain.Flight;
import com.realdolmen.domain.MapperType;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.FlightRepository;
import com.realdolmen.service.FlightService;
import ma.glasnost.orika.MapperFacade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class FlightServiceBean implements FlightService {

    @Inject
    private FlightRepository flightRepo;

    @Inject
    @EntityMapper(type = MapperType.CUSTOMER_FLIGHTS)
    private MapperFacade customerFlightMapper;

    @Override
    public List<CustomerFlightVO> findFlights(String arrivalId, String departureId) {
        List<Flight> vos = flightRepo.getFlightsForAirport(arrivalId, departureId);

        return customerFlightMapper.mapAsList(vos, CustomerFlightVO.class);
    }

}
