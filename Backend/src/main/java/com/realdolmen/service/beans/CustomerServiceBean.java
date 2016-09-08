package com.realdolmen.service.beans;

import com.realdolmen.VO.CustomerLoginVO;
import com.realdolmen.VO.CustomerRegisterVO;
import com.realdolmen.domain.AccountType;
import com.realdolmen.domain.Customer;
import com.realdolmen.domain.MapperType;
import com.realdolmen.qualifiers.EntityMapper;
import com.realdolmen.repository.ApplicationSettingsRepository;
import com.realdolmen.repository.CustomerRepository;
import com.realdolmen.service.CustomerService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.glasnost.orika.MapperFacade;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

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
    @Override
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

    @Override
    public String loginEmp(CustomerLoginVO customer) {
        Customer c;
        try {
            c = repo.getCustomerByEmail(customer.getEmail());
            if (!c.getAccountType().equals(AccountType.EMPLOYEE)){
                return null;
            }
        } catch (Exception e) {
            // NoResultException didn't work.
            return null;
        }

        if (BCrypt.checkpw(customer.getPassword(), c.getPassword())){
            return CreateJwt(c);
        }
        return null;
    }

    private String CreateJwt(Customer customer){
        //check if accounttype is set, if not set normal
        if(customer.getAccountType() == null){
            customer.setAccountType(AccountType.NORMAL);
        }

        final String KEY = applicationSettingsRepo.findValue("JWT_SECRET");
        System.out.println(KEY);
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(customer.getEmail())
                .setIssuedAt(now)
                .claim("userperms", customer.getAccountType())
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }
}
