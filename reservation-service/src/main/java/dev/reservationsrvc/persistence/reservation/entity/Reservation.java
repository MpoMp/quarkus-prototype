package dev.reservationsrvc.persistence.reservation.entity;

import dev.reservationsrvc.util.ValidationUtils;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.Objects;


/**
 *
 */
@NullMarked
@Entity
public class Reservation extends PanacheEntity {

    // Panache encourages public fields, since the getters/setters are generated from it
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private @Nullable String userId;

    protected Reservation() {
        // used by ORM
    }

    public Reservation(Long carId, LocalDate startDate, LocalDate endDate, String userId) {
        this(null, carId, startDate, endDate, userId);
    }

    /**
     * Validates that the start date is not after the end date.
     *
     * @throws IllegalArgumentException if the start date is after the end date
     */
    public Reservation(@Nullable Long id, Long carId, LocalDate startDate, LocalDate endDate, @Nullable String userId) {
        ValidationUtils.validateDateInterval(startDate, endDate);
        this.id = id;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public Long carId() {
        return carId;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public @Nullable String userId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Reservation[" +
                       "id=" + id + ", " +
                       "carId=" + carId + ", " +
                       "startDate=" + startDate + ", " +
                       "endDate=" + endDate + ", " +
                       "userId=" + userId + ']';
    }

}
