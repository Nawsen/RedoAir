package com.realdolmen.repository.beans;

import com.realdolmen.domain.Flight;
import com.realdolmen.repository.FlightRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */

@Stateless
public class FlightRepositoryBean extends AbstractBaseRepository<Flight, Integer> implements FlightRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Flight> getFlightsForAirport(String arrivalId, String departureId){
        return em.createNamedQuery("findFlights", Flight.class)
                .setParameter("departure", departureId)
                .setParameter("arrival", arrivalId)
                .getResultList();
    }
}
