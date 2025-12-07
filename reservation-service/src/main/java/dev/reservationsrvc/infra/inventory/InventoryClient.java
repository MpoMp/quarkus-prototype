package dev.reservationsrvc.infra.inventory;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface InventoryClient {
    List<Car> allCars();
}
