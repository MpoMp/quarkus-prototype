package dev.reservationsrvc.biz.reservation;

import dev.reservationsrvc.persistence.reservation.Reservation;
import dev.reservationsrvc.util.ValidationUtils;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;


/**
 *
 * @param id when empty, it is implied that the instance has not been persisted
 */
@NullMarked
public record ReservationDto(
        Optional<Long> id,
        Long carId,
        LocalDate startDate,
        LocalDate endDate
) {
    public ReservationDto(Long carId, LocalDate startDate, LocalDate endDate) {
        this(Optional.empty(), carId, startDate, endDate);
    }

    /**
     * Validates that the start date is not after the end date.
     *
     * @throws IllegalArgumentException if the start date is after the end date
     */
    public ReservationDto {
        ValidationUtils.validateDateInterval(startDate, endDate);
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
