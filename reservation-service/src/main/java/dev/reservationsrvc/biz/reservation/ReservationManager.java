package dev.reservationsrvc.biz.reservation;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface ReservationManager {
    List<ReservationDto> findAll();

    ReservationDto createReservation(ReservationDto reservationDto);
}
