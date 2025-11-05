package dev.reservationsrvc.controller;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.Optional;

@NullMarked
public record ReservationRsp(
        Long id,
        CarRsp car,
        LocalDate startDate,
        LocalDate endDate
) {
}
