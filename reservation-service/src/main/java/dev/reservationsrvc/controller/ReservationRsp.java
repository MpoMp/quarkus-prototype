package dev.reservationsrvc.controller;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.Optional;

@NullMarked
public record ReservationRsp(
        Long id,
        Long carId,
        LocalDate startDate,
        LocalDate endDate,
        String userId
) {
}
