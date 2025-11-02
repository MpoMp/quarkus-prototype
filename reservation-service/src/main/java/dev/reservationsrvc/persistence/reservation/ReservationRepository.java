package dev.reservationsrvc.persistence.reservation;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface ReservationRepository {
    List<Reservation> findAll();

    Reservation save(Reservation reservation);
}
