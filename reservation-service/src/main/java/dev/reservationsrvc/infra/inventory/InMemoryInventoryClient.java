package dev.reservationsrvc.infra.inventory;

import jakarta.enterprise.context.ApplicationScoped;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@ApplicationScoped
@NullMarked
class InMemoryInventoryClient implements InventoryClient {

    private static final List<Car> CAR_LIST = List.of(
            new Car(10L, "XYZ8940", "Toyota", "Yaris"),
            new Car(11L, "ABC1234", "Honda", "Civic"),
            new Car(12L, "DEF5678", "Ford", "Focus"),
            new Car(13L, "GHI9012", "Chevrolet", "Spark")
    );

    @Override
    public List<Car> allCars() {
        return CAR_LIST;
    }
}
