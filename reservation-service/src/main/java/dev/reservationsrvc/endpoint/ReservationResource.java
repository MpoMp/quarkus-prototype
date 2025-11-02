package dev.reservationsrvc.endpoint;

import dev.reservationsrvc.biz.inventory.CarDto;
import dev.reservationsrvc.biz.inventory.CarManager;
import dev.reservationsrvc.biz.reservation.ReservationDto;
import dev.reservationsrvc.biz.reservation.ReservationManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
@NullMarked
public class ReservationResource {

    private final ReservationManager reservationManager;
    private final CarManager carManager;

    @Inject
    public ReservationResource(ReservationManager reservationManager,
                               CarManager carManager) {
        this.reservationManager = reservationManager;
        this.carManager = carManager;
    }

    /// `http GET http://localhost:8081/reservation/availability startDate==2022-01-01 endDate==2022-01-05`
    ///
    @GET
    @Path("availability")
    public Collection<CarRsp> getAvailableCars(@RestQuery LocalDate startDate,
                                               @RestQuery LocalDate endDate) {
        // TODO errorresponse and proper null handling
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date!");
        }

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
                .map(car -> new CarRsp(car.licensePlate(),
                        car.manufacturer(),
                        car.model()))
                .toList();
    }


}
