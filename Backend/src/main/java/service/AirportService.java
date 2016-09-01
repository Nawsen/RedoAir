package service;

import domain.Airport;
import repository.AirportRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class AirportService {

    @Inject
    AirportRepository repo;

    public List<Airport> getAllAirports(){
        return repo.getAllAirports();
    }
    public List<Airport> getFilteredAirports(String filter){
        return repo.getFilteredAirports(filter);
    }
}
