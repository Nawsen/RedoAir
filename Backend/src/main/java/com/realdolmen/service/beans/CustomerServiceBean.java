package com.realdolmen.service.beans;

import com.auth0.jwt.JWTSigner;
import com.realdolmen.VO.CustomerLoginVO;
import com.realdolmen.VO.CustomerRegisterVO;
import com.realdolmen.domain.ApplicationSettings;
import com.realdolmen.domain.Customer;
import com.realdolmen.domain.MapperType;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.ApplicationSettingsRepository;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.service.CustomerService;
import ma.glasnost.orika.MapperFacade;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Created by WVDAZ49 on 31/08/2016.
 */
@Stateless
public class CustomerServiceBean implements CustomerService {

    @Inject
    private CustomerRepository repo;

    @Inject
    private ApplicationSettingsRepository applicationSettingsRepo;

    @Inject
    @EntityMapper(type = MapperType.CUSTOMER_REGISTER)
    private MapperFacade customerRegisterMapper;

    public void createCustomer(CustomerRegisterVO customer) {
        customer.setPassword(BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()));

        repo.createCustomer(customerRegisterMapper.map(customer, Customer.class));
    }

    public String login(CustomerLoginVO customervo) {
        Customer customer;
        try {
            customer = repo.getCustomerByEmail(customervo.getEmail());
        } catch (Exception e) {
            // NoResultException didn't work.
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        }

        if (BCrypt.checkpw(customervo.getPassword(), customer.getPassword())){
            return CreateJwt(customer);
        }
        return null;
    }

    private String CreateJwt(Customer customer){
        final String issuer = applicationSettingsRepo.findValue("JWT_URL");
        final String secret = applicationSettingsRepo.findValue("JWT_SECRET");

        final long iat = System.currentTimeMillis() / 1000L;
        final long exp = iat + new Long(applicationSettingsRepo.findValue("JWT_VALID_FOR_SECONDS"));

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("firstName", customer.getFirstName());
        claims.put("lastName", customer.getLastName());
        claims.put("email", customer.getEmail());
        claims.put("iat", iat);

        final String jwt = signer.sign(claims);
        return jwt;
    }
}
