package dev.reservationsrvc.infra.inventory;

import dev.inventory.integration.CarView;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface InventoryClient {
    List<CarView> allCars();
}
