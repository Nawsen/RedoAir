package repository;

import domain.Airport;
import domain.Customer;
import domain.Flight;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */

@Stateless
public class CustomerRepository {

    @PersistenceContext
    EntityManager em;

    public void createCustomer(Customer customer) {
        em.persist(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return em.createNamedQuery("getCustomerByEmail", Customer.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<Flight> getFlightsForAirport(String arrivalId, String departureId){
        return em.createNamedQuery("findFlights", Flight.class)
                .setParameter("departure", departureId)
                .setParameter("arrival", arrivalId)
                .getResultList();
    }
}
