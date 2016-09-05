package com.realdolmen.repository;

import com.realdolmen.domain.Customer;
import com.realdolmen.repository.beans.CustomerRepositoryBean;
import com.realdolmen.utilities.persistence.TestData;
import com.realdolmen.utilities.persistence.TestDataLocation;
import org.hibernate.annotations.TypeDef;
import org.junit.Test;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */

public class CustomerRepositoryTest extends AbstractRepositoryTest<CustomerRepositoryBean>{

    @Test
    @TestData(dataSet = TestDataLocation.DATA_CUSTOMERS)
    public void getAllCustomersTest(){
//        assertEquals(5, getRepository().getAllCustomers().size());
    }

    @Test
    @TestData(dataSet = TestDataLocation.DATA_CUSTOMERS)
    public void getCustomerByEmailExpectCorrectResult(){
        assertNotNull(getRepository().getCustomerByEmail("mathias@bulte.xyz"));
        assertEquals("mathias@bulte.xyz", getRepository().getCustomerByEmail("mathias@bulte.xyz").getEmail());
    }
    @Test(expected = NoResultException.class)
    @TestData(dataSet = TestDataLocation.DATA_CUSTOMERS)
    public void getCustomerByEmailExpectNoResultException() {
        getRepository().getCustomerByEmail("emailnotindb");
    }
    
    @Test
    @TestData(dataSet = TestDataLocation.DATA_CUSTOMERS)
    public void createCustomer(){
        Customer customer = new Customer();
        customer.setPassword("wachtwoord");
        customer.setEmail("thisismymail");

        getRepository().getEntityManager().getTransaction().begin();
        getRepository().createCustomer(customer);
        getRepository().getEntityManager().getTransaction().commit();
//        getRepository().getEntityManager().getTransaction().();
//        getRepository().flush();

        // Encryption happens in service, password should be plain text
        assertEquals(customer.getPassword(), getRepository().getCustomerByEmail("thisismymail").getPassword());
        assertEquals(customer.getEmail(), getRepository().getCustomerByEmail("thisismymail").getEmail());

    }


//    @Test
//    @TestData(dataSet = TestDataLocation.DATA_CUSTOMERS)
//    public void checkCustomerPasswordValid


}