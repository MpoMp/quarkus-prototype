package dev.reservationsrvc.biz.reservation;

import dev.reservationsrvc.persistence.reservation.Reservation;
import dev.reservationsrvc.persistence.reservation.ReservationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@ApplicationScoped
@NullMarked
class ReservationManagerImpl implements ReservationManager {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Inject
    ReservationManagerImpl(ReservationRepository reservationRepository,
                           ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
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
        return reservationMapper.mapToDto(savedReservation);
    }
}
