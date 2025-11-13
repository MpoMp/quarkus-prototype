package dev.rentalsrvc.controller;

import dev.rental.integration.RentalRsp;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;


@Path("rental")
@Produces(MediaType.APPLICATION_JSON)
@NullMarked
public class RentalResource {

    private final AtomicLong idGenerator;

    @Inject
    public RentalResource() {
        idGenerator = new AtomicLong(0);
    }


    @Path("/start/{userId}/{reservationId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public RentalRsp make(String userId, Long reservationId) {
        Log.infof("Starting rental for user %s with reservation ID %d", userId, reservationId);

        return new RentalRsp(idGenerator.incrementAndGet(), userId, reservationId, LocalDate.now());
    }


}
