package dev.usersrvc.domain;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

@NullMarked
public record Reservation(
        @Nullable Long id,
        @Nullable String userId,
        Long carId,
        LocalDate startDate,
        LocalDate endDate
) {
}
