package dev.reservationsrvc.biz.inventory;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 *
 * @param id when null, it is implied that the instance has not been persisted
 */
@NullMarked
public record CarDto(
        @Nullable Long id,
        String licensePlate,
        String manufacturer,
        String model
) {
    public CarDto(String licensePlate, String manufacturer, String model) {
        this(null, licensePlate, manufacturer, model);
    }
}
