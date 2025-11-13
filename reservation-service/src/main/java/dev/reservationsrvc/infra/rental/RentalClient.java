package dev.reservationsrvc.infra.rental;

import dev.rental.integration.RentalRsp;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;

@RegisterRestClient(baseUri = "http://localhost:8082")
@Path("/rental")
public interface RentalClient {

    @POST
    @Path("/start/{userId}/{reservationId}")
    RentalRsp start(@RestPath String userId, @RestPath Long reservationId);

}
