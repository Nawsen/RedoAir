package com.realdolmen.service.beans;

import com.realdolmen.domain.Airport;
import com.realdolmen.repository.AirportRepository;
import com.realdolmen.service.AirportService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class AirportServiceBean implements AirportService {

    @Inject
    private AirportRepository repo;

    public List<Airport> getAllAirports(){
        return repo.getAllAirports();
    }
    public List<Airport> getFilteredAirports(String filter){
        if (filter==null){
            throw new IllegalArgumentException("Filter must not be null;");
        }
        return repo.getFilteredAirports(filter);
    }
}
