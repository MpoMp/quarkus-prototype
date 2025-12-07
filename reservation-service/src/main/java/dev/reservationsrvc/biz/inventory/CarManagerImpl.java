package dev.reservationsrvc.biz.inventory;

import dev.reservationsrvc.infra.inventory.GraphQLInventoryClient;
import dev.reservationsrvc.infra.inventory.InventoryClient;
import io.smallrye.graphql.client.GraphQLClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@ApplicationScoped
@NullMarked
public class CarManagerImpl implements CarManager {

    private final InventoryClient inventoryClient;
    private final CarMapper carMapper;

    @Inject
    public CarManagerImpl(@GraphQLClient("inventory") GraphQLInventoryClient inventoryClient,
                          CarMapper carMapper) {
        this.inventoryClient = inventoryClient;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDto> findAllCars() {
        return inventoryClient.allCars().stream()
                       .map(carMapper::mapToDto)
                       .toList();
    }
}
