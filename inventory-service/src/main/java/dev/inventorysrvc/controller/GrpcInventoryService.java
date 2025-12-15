package dev.inventorysrvc.controller;

import dev.inventorysrvc.contract.CarResponse;
import dev.inventorysrvc.contract.InsertCarRequest;
import dev.inventorysrvc.contract.RemoveCarRequest;
import dev.inventorysrvc.persistence.Car;
import dev.inventorysrvc.persistence.CarInventory;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;


@GrpcService
@NullMarked
public class GrpcInventoryService implements dev.inventorysrvc.contract.InventoryService {

    private final CarInventory carInventory;
    private final CarMapper carMapper;

    @Inject
    public GrpcInventoryService(CarInventory carInventory, CarMapper carMapper) {
        this.carInventory = carInventory;
        this.carMapper = carMapper;
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

        if(carOptional.isPresent()){
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
        }else{
            return Uni.createFrom().nullItem();
        }}
}
