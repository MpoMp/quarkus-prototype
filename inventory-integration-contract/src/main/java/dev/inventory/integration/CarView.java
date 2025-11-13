package dev.inventory.integration;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record CarView(
        Long id,
        String licensePlate,
        String manufacturer,
        String model
) { }
