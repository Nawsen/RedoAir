package com.realdolmen.filters;


import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.realdolmen.qualifiers.Auth;
import com.realdolmen.repository.ApplicationSettingsRepository;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Map;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
@Provider
@Auth
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String KEY = applicationSettingsRepository.findValue("JWT_SECRET");

        try {
            if (requestContext.getHeaderString("Authorization") != null) {
                //we need to split off the non usefull things from the header
                //in this case we need to chew off "Bearer "
                String code = requestContext.getHeaderString("Authorization").split(" ")[1];
                Claims claim = Jwts.parser().setSigningKey(KEY).parseClaimsJws(code).getBody();
                //after we check if the claim is correct lets set the email into the header
                //with this we can identify the user
                requestContext.getHeaders().add("email", claim.getId());
            } else {
                throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
            }
            //requestContext.getHeaders().add("email", claims.get("email").toString());
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}