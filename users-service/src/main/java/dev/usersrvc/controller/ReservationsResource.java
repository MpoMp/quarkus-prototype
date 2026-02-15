package dev.usersrvc.controller;

import dev.usersrvc.domain.Car;
import dev.usersrvc.domain.Reservation;
import dev.usersrvc.infra.ReservationsClient;
import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDate;
import java.util.Collection;

@Path("/")
public class ReservationsResource {

    // alternative to simple template injection; Qute performs check at build time with this one
    @CheckedTemplate
    private static class Templates {

        public static native TemplateInstance index(
                LocalDate startDate,
                LocalDate endDate,
                String name
        );

        public static native TemplateInstance listofreservations(Collection<Reservation> reservations);

        public static native TemplateInstance availablecars(Collection<Car> cars,
                                                            LocalDate startDate,
                                                            LocalDate endDate);
    }

    private final SecurityContext securityContext;
    private final ReservationsClient reservationsClient;

    @Inject
    public ReservationsResource(SecurityContext securityContext,
                                @RestClient ReservationsClient reservationsClient) {
        this.securityContext = securityContext;
        this.reservationsClient = reservationsClient;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index(@RestQuery LocalDate startDate,
                                  @RestQuery LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.now().plusDays(1);
        }

        if (endDate == null) {
            endDate = LocalDate.now().plusDays(7);
        }

        return Templates.index(startDate,
                               endDate,
                               securityContext.getUserPrincipal().getName()
        );
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/get")
    public TemplateInstance getReservations() {
        Collection<ReservationsClient.ReservationRsp> reservations = reservationsClient.allReservations();

        return Templates.listofreservations(reservations.stream()
                                                    .map(rsp -> new Reservation(rsp.id,
                                                                                rsp.userId,
                                                                                rsp.carId,
                                                                                rsp.startDate,
                                                                                rsp.endDate))
                                                    .toList()
        );
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/available")
    public TemplateInstance availableCars(@RestQuery LocalDate startDate,
                                          @RestQuery LocalDate endDate) {
        Collection<Car> availableCars = reservationsClient.availability(startDate, endDate);

        return Templates.availablecars(availableCars, startDate, endDate);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path(("/reserve"))
    public RestResponse<TemplateInstance> create(@RestForm LocalDate startDate,
                                                 @RestForm LocalDate endDate,
                                                 @RestForm Long carId) {
        Reservation reservation = new Reservation(null, null, carId, startDate, endDate);

        Reservation createdReservation = reservationsClient.make(reservation);

        Log.debug("Reservation created: " + createdReservation);

        return RestResponse.ResponseBuilder
                       .ok(getReservations())
                       .header("HX-Trigger-After-Swap", "update-available-cars-list")
                       .build();
    }
}
