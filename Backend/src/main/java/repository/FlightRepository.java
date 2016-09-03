package repository;

import domain.Airport;
import domain.Flight;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */

@Stateless
public class FlightRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Flight> getFlightsForAirport(String arrivalId, String departureId){
        return em.createNamedQuery("findFlights", Flight.class)
                .setParameter("departure", departureId)
                .setParameter("arrival", arrivalId)
                .getResultList();
    }
}
