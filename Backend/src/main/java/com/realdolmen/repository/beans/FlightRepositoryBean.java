package com.realdolmen.repository.beans;

import com.realdolmen.domain.Flight;
import com.realdolmen.domain.SeatType;
import com.realdolmen.domain.Ticket;
import com.realdolmen.repository.FlightRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */

@Stateless
public class FlightRepositoryBean extends AbstractBaseRepository<Flight, Long> implements FlightRepository {

    @Override
    public List<Flight> getFlightsForAirport(String arrivalId, String departureId){
        return getEntityManager().createNamedQuery("findFlights", Flight.class)
                .setParameter("departure", departureId)
                .setParameter("arrival", arrivalId)
                .getResultList();
    }

    @Override
    public Flight getFlightFromId(Long flightId) {
        return findByPrimaryKey(flightId);
    }

    @Override
    public Flight getFlightFromFlightNumber(Long flightNumber) {
        return getEntityManager().createNamedQuery("findFlightByFlightNumber", Flight.class)
                .setParameter("number", flightNumber.toString())
                .getSingleResult();
    }

    @Override
    public List<Flight> findAllAvailableFlights() {
        return getEntityManager().createNamedQuery("allAvailableFlights", Flight.class)
                .setParameter("now", new Date())
                .getResultList();
    }

}
