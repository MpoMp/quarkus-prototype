package dev.usersrvc.domain;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record Car(
        Long id,
        String licensePlate,
        String manufacturer,
        String model
) {
}
