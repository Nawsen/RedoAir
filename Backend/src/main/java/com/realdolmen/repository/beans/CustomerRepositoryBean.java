package com.realdolmen.repository.beans;


import com.realdolmen.domain.Customer;
import com.realdolmen.domain.Flight;
import com.realdolmen.repository.CustomerRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */

@Stateless
public class CustomerRepositoryBean extends AbstractBaseRepository<Customer, Long> implements CustomerRepository{

    public void createCustomer(Customer customer) {
        getEntityManager().persist(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return getEntityManager().createNamedQuery("getCustomerByEmail", Customer.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
