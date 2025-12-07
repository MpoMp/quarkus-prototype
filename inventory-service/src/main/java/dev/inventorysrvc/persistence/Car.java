package dev.inventorysrvc.persistence;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public record Car(
        @Nullable Long id,
        String licensePlate,
        String manufacturer,
        String model
) {
}
