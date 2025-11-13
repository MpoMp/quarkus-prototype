package dev.rental.integration;

import java.time.LocalDate;

public record RentalRsp(
        Long id,
        String userId,
        Long reservationId,
        LocalDate startDate
) {
}
