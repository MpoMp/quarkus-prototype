package dev.reservationsrvc.infra.inventory;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public record Car(
        Long id,
        String licensePlate,
        String manufacturer,
        String model
) { }
