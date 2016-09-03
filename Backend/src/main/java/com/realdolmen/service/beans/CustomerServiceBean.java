package com.realdolmen.service.beans;

import com.auth0.jwt.JWTSigner;
import com.realdolmen.domain.Customer;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.repository.beans.CustomerRepositoryBean;
import com.realdolmen.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class CustomerServiceBean implements CustomerService {

    @Inject
    private CustomerRepository repo;

    public void createCustomer(Customer customer) {
        repo.createCustomer(customer);
    }

    public String login(Customer customer) {
        if (BCrypt.checkpw(customer.getLoginPassword(), repo.getCustomerByEmail(customer.getEmail()).getPassword())){
            final String issuer = "https://localhost:8080/";
            final String secret = "Very_Secret";

            final long iat = System.currentTimeMillis() / 1000L;
            final long exp = iat + 120L;

            final JWTSigner signer = new JWTSigner(secret);
            final HashMap<String, Object> claims = new HashMap<String, Object>();
            claims.put("iss", issuer);
            claims.put("exp", exp);
            claims.put("firstName", customer.getFirstName());
            claims.put("lastName", customer.getLastName());
            claims.put("iat", iat);

            final String jwt = signer.sign(claims);
            return jwt;
        }
        return null;
    }
}
