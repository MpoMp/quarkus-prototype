package dev.reservationsrvc.controller;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationReq(
        @NotNull Long carId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate
) {
}
