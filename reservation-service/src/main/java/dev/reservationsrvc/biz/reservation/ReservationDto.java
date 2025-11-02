package dev.reservationsrvc.biz.reservation;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.Objects;


/**
 *
 * @param id when null, it is implied that the instance has not been persisted
 */
@NullMarked
public record ReservationDto(
        @Nullable Long id,
        Long carId,
        LocalDate startDate,
        LocalDate endDate
) {
    public ReservationDto(Long carId, LocalDate startDate, LocalDate endDate) {
        this(null, carId, startDate, endDate);
    }

    /**
     * Validates that the start date is not after the end date.
     *
     * @throws IllegalArgumentException if the start date is after the end date
     */
    public ReservationDto {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Determines if the current reservation overlaps with the given time period.
     */
    public boolean isOverlapping(LocalDate startDate,
                                 LocalDate endDate) {
        return !(startDate.isAfter(endDate) || endDate.isBefore(startDate));
    }
}
