package dev.inventorysrvc.controller;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public record CarView(
        @Nullable Long id,
        String licensePlate,
        String manufacturer,
        String model
) { }
