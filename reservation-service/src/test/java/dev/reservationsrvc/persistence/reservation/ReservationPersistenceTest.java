package dev.reservationsrvc.persistence.reservation;

import dev.reservationsrvc.persistence.reservation.entity.Reservation;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
class ReservationPersistenceTest {

    @Test
    public void createReservation() {
        Reservation reservation = createAndSave();

        List<Reservation> results = Reservation.listAll();

        assertThat(results).hasSize(1);

        Reservation result = results.getFirst();
        assertThat(result.id).isNotNull();
        assertThat(result.carId()).isEqualTo(reservation.carId());
        assertThat(result.startDate()).isEqualTo(reservation.startDate());
        assertThat(result.endDate()).isEqualTo(reservation.endDate());
        assertThat(result.userId()).isEqualTo(reservation.userId());
    }

    // transactional methods cannot be private
    @Transactional
    static @NonNull Reservation createAndSave() {
        Reservation reservation = new Reservation(384L,
                                                  LocalDate.now().plusDays(5),
                                                  LocalDate.now().plusDays(12),
                                                  "someone"
        );

        reservation.persist();
        return reservation;
    }
}