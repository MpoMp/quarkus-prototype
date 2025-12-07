package dev.inventorysrvc.controller;


import dev.inventorysrvc.persistence.Car;
import dev.inventorysrvc.persistence.CarInventory;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class InventoryGqlApi {

    private final CarInventory carInventory;
    private final CarMapper carMapper;

    @Inject
    public InventoryGqlApi(CarInventory carInventory,
                           CarMapper carMapper) {
        this.carInventory = carInventory;
        this.carMapper = carMapper;
    }


    @Query
    public List<CarView> cars(){
        return carInventory.getAllCars().stream()
                       .map(carMapper::map)
                       .toList();
    }

    @Mutation
    public CarView register(CarView car){
        Car savedCar = carInventory.saveCar(carMapper.map(car));

        return carMapper.map(savedCar);
    }

    @Mutation
    public boolean remove(String licensePlate){
        List<Car> cars = carInventory.getAllCars();

        return cars.removeIf(car -> car.licensePlate().equals(licensePlate));

    }

}
