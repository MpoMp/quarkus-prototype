package dev.reservationsrvc.controller;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record CarRsp(
        String licensePlate,
        String manufacturer,
        String model
) {
}
