package dev.reservationsrvc.persistence.reservation;

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
        String carId,
        LocalDate startDate,
        LocalDate endDate
) {
    public Reservation(String carId, LocalDate startDate, LocalDate endDate) {
        this(null, carId, startDate, endDate);
    }

    /**
     * Validates that the start date is not after the end date.
     *
     * @throws IllegalArgumentException if the start date is after the end date
     */
    public Reservation {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date!");
        }
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

    /**
     * Determines if the current reservation overlaps with another reservation.
     *
     * @param other the reservation to compare with the current reservation
     * @return true if the reservations overlap, false otherwise
     */
    public boolean isOverlapping(Reservation other) {
        return !(startDate.isAfter(other.endDate) || endDate.isBefore(other.startDate));
    }

    public Reservation copyWithId(Long id) {
        return new Reservation(id, carId, startDate, endDate);
    }
}
