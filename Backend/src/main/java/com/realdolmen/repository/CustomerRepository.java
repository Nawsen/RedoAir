package com.realdolmen.repository;

import com.realdolmen.domain.Customer;
import com.realdolmen.domain.Flight;

import java.util.List;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public interface CustomerRepository extends Repository<Customer, Integer>{

    void createCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    List<Flight> getFlightsForAirport(String arrivalId, String departureId);
}
