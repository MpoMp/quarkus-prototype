package dev.reservationsrvc.persistence.reservation;

import dev.reservationsrvc.util.ValidationUtils;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.Objects;


/**
 *
 * @param id when null, it is implied that the instance has not been persisted
 */
@NullMarked
public record Reservation(
        @Nullable Long id,
        Long carId,
        LocalDate startDate,
        LocalDate endDate
) {
    public Reservation(Long carId, LocalDate startDate, LocalDate endDate) {
        this(null, carId, startDate, endDate);
    }

    /**
     * Validates that the start date is not after the end date.
     *
     * @throws IllegalArgumentException if the start date is after the end date
     */
    public Reservation {
        ValidationUtils.validateDateInterval(startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Reservation copyWithId(Long id) {
        return new Reservation(id, carId, startDate, endDate);
    }
}
