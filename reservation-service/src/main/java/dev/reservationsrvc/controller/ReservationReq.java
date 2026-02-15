package dev.reservationsrvc.controller;

import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;

@NullMarked
public record ReservationReq(
        Long carId,
        LocalDate startDate,
        LocalDate endDate
) { }
