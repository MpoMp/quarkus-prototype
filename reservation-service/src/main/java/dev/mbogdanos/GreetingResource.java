package dev.mbogdanos;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.security.Principal;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "greeting")
    String greetingMessage;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingMessage;
    }

    /// Invoke with HTTPie; `http :8080/hello/whoami "Authorization:Bearer TOKEN"`
    ///
    /// Use the Keycloak access token.
    @GET
    @Path("/whoami")
    @Produces(MediaType.TEXT_PLAIN)
    public String identifierApi(@Context SecurityContext securityContext) {
        Principal userPrincipal = securityContext.getUserPrincipal();
        if (userPrincipal != null) {
            return userPrincipal.getName();
        }else {
            return "N/A";
        }
    }
}
