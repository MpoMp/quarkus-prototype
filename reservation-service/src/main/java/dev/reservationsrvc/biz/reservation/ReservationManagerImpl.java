package dev.reservationsrvc.biz.reservation;

import dev.reservationsrvc.infra.rental.RentalClient;
import dev.reservationsrvc.persistence.reservation.entity.Reservation;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@NullMarked
class ReservationManagerImpl implements ReservationManager {

    private final ReservationMapper reservationMapper;
    private final RentalClient rentalClient;

    @Inject
    ReservationManagerImpl(ReservationMapper reservationMapper,
                           @RestClient RentalClient rentalClient) {
        this.reservationMapper = reservationMapper;
        this.rentalClient = rentalClient;
    }

    @Override
    public List<ReservationDto> findAll() {
        List<Reservation> reservations = Reservation.listAll();

        return reservations.stream()
                       .map(reservationMapper::mapToDto)
                       .toList();
    }

    @Override
    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation savedReservation = reservationMapper.mapFromDto(reservationDto);
        savedReservation.persist();

        if (savedReservation.startDate().equals(LocalDate.now())) {
            //TODO proper user ID
            var rentalRsp = rentalClient.start("RSRV-SRVC", savedReservation.id);
            Log.infof("Rental started for reservation ID %d with rental ID %d", savedReservation.id, rentalRsp.id());
        }

        return reservationMapper.mapToDto(savedReservation);
    }
}
