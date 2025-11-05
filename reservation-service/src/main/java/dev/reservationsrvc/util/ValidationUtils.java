package dev.reservationsrvc.util;

import java.time.LocalDate;

public final class ValidationUtils {

    private ValidationUtils() {
        // utility class
    }

    /**
     * Validates that the specified date interval is valid.
     *
     * @throws IllegalArgumentException if the start date is after the end date
     */
    public static void validateDateInterval(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date!");
        }
    }
}
