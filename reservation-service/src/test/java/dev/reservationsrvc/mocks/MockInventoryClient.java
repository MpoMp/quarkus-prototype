package dev.reservationsrvc.mocks;

import dev.reservationsrvc.infra.inventory.Car;
import dev.reservationsrvc.infra.inventory.GraphQLInventoryClient;
import io.quarkus.test.Mock;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@Mock // this will inject this implementation over the real one during testing; alternative is Mockito
@NullMarked
public class MockInventoryClient implements GraphQLInventoryClient {

    @Override
    public List<Car> allCars() {
        return List.of(
                new Car(111L, "ABC1234", "Peugeot", "506")
        );
    }
}
