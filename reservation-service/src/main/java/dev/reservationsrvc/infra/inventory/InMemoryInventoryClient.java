package dev.reservationsrvc.infra.inventory;

import dev.inventory.integration.CarView;
import jakarta.enterprise.context.ApplicationScoped;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@ApplicationScoped
@NullMarked
class InMemoryInventoryClient implements InventoryClient {

    private static final List<CarView> CAR_LIST = List.of(
            new CarView(10L, "XYZ8940", "Toyota", "Yaris"),
            new CarView(11L, "ABC1234", "Honda", "Civic"),
            new CarView(12L, "DEF5678", "Ford", "Focus"),
            new CarView(13L, "GHI9012", "Chevrolet", "Spark")
    );

    @Override
    public List<CarView> allCars() {
        return CAR_LIST;
    }
}
