package com.realdolmen.service;

import com.realdolmen.domain.Airport;

import java.util.List;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
public interface AirportService {
    List<Airport> getAllAirports();
    List<Airport> getFilteredAirports(String filter);
}
