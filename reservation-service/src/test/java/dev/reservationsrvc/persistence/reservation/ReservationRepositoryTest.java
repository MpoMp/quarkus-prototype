package dev.reservationsrvc.persistence.reservation;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
class ReservationRepositoryTest {

    @Inject
    ReservationRepository unitUnderTest;

    @Test
    public void createReservation() {
        Reservation reservation = new Reservation(384L,
                                                  LocalDate.now().plusDays(5),
                                                  LocalDate.now().plusDays(12)
        );

        Reservation result = unitUnderTest.save(reservation);

        assertThat(result.id()).isNotNull();

        assertThat(result).isEqualTo(reservation.copyWithId(result.id()));
    }
}