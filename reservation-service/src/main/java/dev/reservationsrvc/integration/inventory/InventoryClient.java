package dev.reservationsrvc.integration.inventory;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface InventoryClient {
    List<Car> allCars();
}
