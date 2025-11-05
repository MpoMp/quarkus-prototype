package dev.reservationsrvc.infra.inventory;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record Car(
        Long id,
        String licensePlate,
        String manufacturer,
        String model
) { }
