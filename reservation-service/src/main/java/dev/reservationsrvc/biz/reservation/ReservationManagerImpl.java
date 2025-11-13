package dev.reservationsrvc.biz.reservation;

import dev.rental.integration.RentalRsp;
import dev.reservationsrvc.infra.rental.RentalClient;
import dev.reservationsrvc.persistence.reservation.Reservation;
import dev.reservationsrvc.persistence.reservation.ReservationRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@NullMarked
class ReservationManagerImpl implements ReservationManager {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final RentalClient rentalClient;

    @Inject
    ReservationManagerImpl(ReservationRepository reservationRepository,
                           ReservationMapper reservationMapper,
                           @RestClient RentalClient rentalClient) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.rentalClient = rentalClient;
    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationRepository.findAll().stream()
                       .map(reservationMapper::mapToDto)
                       .toList();
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation savedReservation = reservationRepository.save(reservationMapper.mapFromDto(reservationDto));

        if (savedReservation.startDate().equals(LocalDate.now())) {
            //TODO proper user ID
            var rentalRsp = rentalClient.start("-1", savedReservation.id());
            Log.infof("Rental started for reservation ID %d with rental ID %d", savedReservation.id(), rentalRsp.id());
        }

        return reservationMapper.mapToDto(savedReservation);
    }
}
