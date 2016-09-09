package com.realdolmen.service;

import com.realdolmen.VO.CustomerLoginVO;
import com.realdolmen.VO.CustomerRegisterVO;
import com.realdolmen.domain.AccountType;
import com.realdolmen.domain.Customer;
import com.realdolmen.repository.ApplicationSettingsRepository;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.service.beans.CustomerServiceBean;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by WVDAZ49 on 9/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceBean service;

    @Mock
    private CustomerRepository repo;

    @Mock
    private ApplicationSettingsRepository applicationSettingsRepo;

    @Mock
    private MapperFacade customerRegisterMapper;

    private CustomerRegisterVO registerVO;
    private Customer customer;
    private CustomerLoginVO loginVO;

    @Before
    public void setup() {
        customer = new Customer();
        customer.setEmail("test@test.be");
        customer.setFirstName("tester");
        customer.setLastName("Van Tester");
        customer.setPassword(BCrypt.hashpw("passwoord", BCrypt.gensalt()));

        registerVO = new CustomerRegisterVO();
        registerVO.setEmail("test@test.be");
        registerVO.setFirstName("tester");
        registerVO.setLastName("Van Tester");
        registerVO.setPassword("passwoord");

        loginVO = new CustomerLoginVO();
        loginVO.setEmail("test@test.be");
        loginVO.setPassword("passwoord");

    }

    @Test
    public void testIfUserCreateSuccess() {
        when(customerRegisterMapper.map(registerVO, Customer.class)).thenReturn(customer);
        service.createCustomer(registerVO);
        verify(repo, times(1)).createCustomer(customer);
    }

    @Test
    public void testIfRegularUserCanLogin(){
        customer.setPassword(BCrypt.hashpw("wrong", BCrypt.gensalt()));
        when(repo.getCustomerByEmail(loginVO.getEmail())).thenReturn(customer);
        String jwt = service.login(loginVO);
        verify(repo, times(1)).getCustomerByEmail(loginVO.getEmail());
        assertEquals(null, jwt);
    }
    @Test
    public void testIfRegularUserCanLoginCorrect(){
        when(repo.getCustomerByEmail(loginVO.getEmail())).thenReturn(customer);
        when(applicationSettingsRepo.findValue("JWT_SECRET")).thenReturn("secret");
        service.login(loginVO);
        verify(repo, times(1)).getCustomerByEmail(loginVO.getEmail());
        
    }
    @Test
    public void testIfEmployeeUserCanLogin(){
        customer.setAccountType(AccountType.EMPLOYEE);
        customer.setPassword(BCrypt.hashpw("wrong", BCrypt.gensalt()));
        when(repo.getCustomerByEmail(loginVO.getEmail())).thenReturn(customer);
        String jwt = service.loginEmp(loginVO);
        verify(repo, times(1)).getCustomerByEmail(loginVO.getEmail());
        assertEquals(null, jwt);
    }
    @Test
    public void testIfEmployeeUserCanLoginNotEmpl(){
        customer.setAccountType(AccountType.NORMAL);
        when(repo.getCustomerByEmail(loginVO.getEmail())).thenReturn(customer);
        when(applicationSettingsRepo.findValue("JWT_SECRET")).thenReturn("secret");
        String jwt = service.loginEmp(loginVO);
        verify(repo, times(1)).getCustomerByEmail(loginVO.getEmail());
        assertEquals(null, jwt);
    }
    @Test
    public void testIfEmployeeUserCanLoginCorrect(){
        when(repo.getCustomerByEmail(loginVO.getEmail())).thenReturn(customer);
        when(applicationSettingsRepo.findValue("JWT_SECRET")).thenReturn("secret");
        service.loginEmp(loginVO);
        verify(repo, times(1)).getCustomerByEmail(loginVO.getEmail());

    }
}
