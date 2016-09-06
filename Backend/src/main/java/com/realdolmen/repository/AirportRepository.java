package com.realdolmen.repository;

import com.realdolmen.domain.Airport;

import java.util.List;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public interface AirportRepository extends Repository<Airport, Long> {

    List<Airport> getAllAirports();
    List<Airport> getFilteredAirports(String filter);

}
