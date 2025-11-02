package dev.reservationsrvc.persistence.reservation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@NullMarked
class InMemoryReservationRepository implements ReservationRepository {

    private final AtomicLong idGenerator;
    private final List<Reservation> reservations;

    @Inject
    InMemoryReservationRepository() {
        idGenerator = new AtomicLong(0);
        reservations = new CopyOnWriteArrayList<>();
    }

    @Override
    public List<Reservation> findAll() {
        return reservations;
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.id() == null) {
            reservation = reservation.copyWithId(idGenerator.incrementAndGet());
        } else {
            reservations.remove(reservation);
        }

        reservations.add(reservation);
        return reservation;
    }
}
