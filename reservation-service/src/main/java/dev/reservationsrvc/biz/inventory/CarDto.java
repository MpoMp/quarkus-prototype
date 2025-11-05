package dev.reservationsrvc.biz.inventory;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record CarDto(
        Long id,
        String licensePlate,
        String manufacturer,
        String model
) { }
