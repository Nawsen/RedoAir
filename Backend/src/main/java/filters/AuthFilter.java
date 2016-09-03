package filters;


import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

/**
 * Created by WVDAZ49 on 2/09/2016.
 */
@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String secret = "{{secret used for signing}}";
        System.out.println(requestContext.getHeaderString("Authorization"));
        try {
            final JWTVerifier verifier = new JWTVerifier(secret, "{{my-audience}}", "{{my-issuer}}");
            final Map<String,Object> claims= verifier.verify(requestContext.getHeaderString("Authorization"));
            System.out.println(claims.get("firstName"));
            System.out.println(claims.get("lastName"));
        } catch (JWTVerifyException e) {
            System.out.println("failed small");
        } catch (Exception e) {
            System.out.println("failed big");
            e.printStackTrace();
        }
    }

}