package service;

import domain.Customer;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
public interface CustomerService {

    void createCustomer(Customer customer);
    String login(Customer customer);
}
