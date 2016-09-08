package com.realdolmen.filters;


import com.realdolmen.domain.AccountType;
import com.realdolmen.qualifiers.Auth;
import com.realdolmen.repository.ApplicationSettingsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
@Provider
@Auth
public class RegularAuthFilter implements ContainerRequestFilter {

    @Inject
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String KEY = applicationSettingsRepository.findValue("JWT_SECRET");

        try {
            if (requestContext.getHeaderString("Authorization") != null) {
                //we need to split off the non usefull things from the header
                //in this case we need to chew off "Bearer"
                String code = requestContext.getHeaderString("Authorization").split(" ")[1];
                Claims claim = Jwts.parser().setSigningKey(KEY).parseClaimsJws(code).getBody();
                //after we check if the claim is correct lets set the email into the header
                //with this we can identify the user

                if (claim.get("userperms").equals(AccountType.NORMAL.toString()) ||
                        claim.get("userperms").equals(AccountType.EMPLOYEE.toString())){
                    requestContext.getHeaders().add("email", claim.getId());
                } else {
                    throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else {
                throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}