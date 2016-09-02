package service;

import domain.Customer;
import org.mindrot.jbcrypt.BCrypt;
import repository.CustomerRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class CustomerService {

    @Inject
    CustomerRepository repo;

//    public List<Customer> getAllCustomers(){
//        return repo.getAllCustomers();
//    }
//    public List<Customer> getFilteredCustomers(String filter){
//        return repo.getFilteredCustomers(filter);
//    }
    public void createCustomer(Customer customer) {
        repo.createCustomer(customer);
    }

    public boolean login(Customer customer) {
        return BCrypt.checkpw(customer.getLoginPassword(), repo.getCustomerByEmail(customer.getEmail()).getPassword());
    }
}
