package dev.usersrvc.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("/whoami")
public class UserLandingResource {

    /**
     * Needs to match the name of the template file.
     */
    private final Template whoami;
    private final SecurityContext securityContext;

    @Inject
    public UserLandingResource(Template whoami, SecurityContext securityContext) {
        this.whoami = whoami;
        this.securityContext = securityContext;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance userData() {
        String userId = (securityContext.getUserPrincipal() != null)
                                ? securityContext.getUserPrincipal().getName()
                                : null;
        return whoami.data("name", userId);
    }
}
