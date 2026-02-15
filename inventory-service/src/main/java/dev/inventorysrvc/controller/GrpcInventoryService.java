package dev.inventorysrvc.controller;

import dev.inventorysrvc.contract.CarResponse;
import dev.inventorysrvc.contract.InsertCarRequest;
import dev.inventorysrvc.contract.RemoveCarRequest;
import dev.inventorysrvc.persistence.Car;
import dev.inventorysrvc.persistence.CarInventory;
import io.quarkus.grpc.GrpcService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.jspecify.annotations.NullMarked;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;


@GrpcService
@NullMarked
public class GrpcInventoryService implements dev.inventorysrvc.contract.InventoryService {

    private final CarInventory carInventory;

    @Inject
    public GrpcInventoryService(CarInventory carInventory) {
        this.carInventory = carInventory;
    }

    @Override
    public Uni<CarResponse> add(InsertCarRequest request) {
        Car newCar = new Car(null,
                             request.getLicensePlateNumber(),
                             request.getManufacturer(),
                             request.getModel());

        Car car = carInventory.saveCar(newCar);

        return Uni.createFrom()
                       .item(
                               CarResponse.newBuilder()
                                       .setLicensePlateNumber(car.licensePlate())
                                       .setManufacturer(car.manufacturer())
                                       .setModel(car.model())
                                       .setId(car.id())
                                       .build()
                       );
    }

    @Override
    public Uni<CarResponse> remove(RemoveCarRequest request) {
        List<Car> cars = carInventory.getAllCars();

        Optional<Car> carOptional = cars.stream().filter(car -> car.licensePlate().equals(request.getLicensePlateNumber()))
                                            .findFirst();

        if (carOptional.isPresent()) {
            Car removedCar = carOptional.get();
            cars.remove(removedCar);

            return Uni.createFrom()
                           .item(
                                   CarResponse.newBuilder()
                                           .setLicensePlateNumber(removedCar.licensePlate())
                                           .setManufacturer(removedCar.manufacturer())
                                           .setModel(removedCar.model())
                                           .setId(removedCar.id())
                                           .build()
                           );
        } else {
            return Uni.createFrom().nullItem();
        }
    }

    /**
     * Uses bidirectional gRPC streaming.
     */
    @Override
    public Multi<CarResponse> addStreaming(Multi<InsertCarRequest> requestStream) {
        return requestStream
                       .map(req -> {
                                Car newCar = new Car(null,
                                                     req.getLicensePlateNumber(),
                                                     req.getManufacturer(),
                                                     req.getModel());

                                return carInventory.saveCar(newCar);
                            }
                       )
                       .onItem()
                       .invoke(car -> {
                           Log.info("Persisted car: " + car);
                           // we could be saving the car here
                       })
                       .map(car -> CarResponse.newBuilder()
                                           .setLicensePlateNumber(car.licensePlate())
                                           .setManufacturer(car.manufacturer())
                                           .setModel(car.model())
                                           .setId(car.id())
                                           .build());
    }

}
