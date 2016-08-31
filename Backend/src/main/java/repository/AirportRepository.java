package repository;

import domain.Airport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class AirportRepository {
    @PersistenceContext
    EntityManager em;

    public List<Airport> getAllAirports(){
        return em.createNamedQuery("getAll", Airport.class).getResultList();
    }
}
