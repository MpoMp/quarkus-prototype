package dev.reservationsrvc.controller;

import dev.reservationsrvc.biz.inventory.CarDto;
import dev.reservationsrvc.biz.inventory.CarManager;
import dev.reservationsrvc.biz.reservation.ReservationDto;
import dev.reservationsrvc.biz.reservation.ReservationManager;
import dev.reservationsrvc.util.ValidationUtils;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
@NullMarked
public class ReservationResource {

    private final ReservationManager reservationManager;
    private final CarManager carManager;
    private final ReservationReqRspMapper reservationReqRspMapper;

    @Inject
    public ReservationResource(ReservationManager reservationManager,
                               CarManager carManager,
                               ReservationReqRspMapper reservationReqRspMapper) {
        this.reservationManager = reservationManager;
        this.carManager = carManager;
        this.reservationReqRspMapper = reservationReqRspMapper;
    }

    /**
     * When defined within a class, such a mapper is wired only for the methods of that class.
     */
    @ServerExceptionMapper
    public RestResponse<String> mapException(IllegalArgumentException iae) {
        // TODO not serialized to proper JSON?  https://github.com/quarkusio/quarkus/issues/36155
        return RestResponse.status(Response.Status.BAD_REQUEST, iae.getLocalizedMessage());
    }

    ///
    /// Invoke with: ```http GET :8081/reservation/availability startDate==2022-01-01 endDate==2022-01-05```
    ///
    @GET
    @Path("availability")
    public Collection<CarRsp> getAvailableCars(@NotNull @RestQuery LocalDate startDate,
                                               @NotNull @RestQuery LocalDate endDate) {
        ValidationUtils.validateDateInterval(startDate, endDate);

        List<CarDto> availableCars = carManager.findAllCars();

        Map<Long, CarDto> carsById = new HashMap<>();
        for (CarDto car : availableCars) {
            carsById.put(car.id(), car);
        }

        List<ReservationDto> reservations = reservationManager.findAll();

        for (ReservationDto reservation : reservations) {
            if (reservation.isOverlapping(startDate, endDate)) {
                carsById.remove(reservation.carId());
            }
        }

        return carsById.values().stream()
                .map(car -> new CarRsp(car.id(),
                                       car.licensePlate(),
                                       car.manufacturer(),
                                       car.model()))
                .toList();
    }

    /// Better test with [Swagger UI;](http://localhost:8081/q/swagger-ui/). :)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ReservationRsp make(@Valid ReservationReq reservationRequest) {
        ValidationUtils.validateDateInterval(reservationRequest.startDate(), reservationRequest.endDate());
        var reservationDto = reservationReqRspMapper.mapToDto(reservationRequest);

        ReservationDto createdReservation = reservationManager.createReservation(reservationDto);
        return reservationReqRspMapper.mapFromDto(createdReservation);
    }


}
