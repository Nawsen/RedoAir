package com.realdolmen.service;

import com.realdolmen.VO.CustomerLoginVO;
import com.realdolmen.VO.CustomerRegisterVO;
import com.realdolmen.domain.Customer;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
public interface CustomerService {

    void createCustomer(CustomerRegisterVO customer);
    String login(CustomerLoginVO customer);
}
