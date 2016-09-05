package com.realdolmen.filters;


import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.realdolmen.qualifiers.Auth;

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

    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String secret = "Very_Secret";
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String,Object> claims = verifier.verify(requestContext.getHeaderString("Authorization"));
            requestContext.getHeaders().add("email", claims.get("email").toString());
        } catch (JWTVerifyException e) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}