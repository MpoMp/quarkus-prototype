package dev.inventorysrvc.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class CarInventory {

    private List<Car> cars = new ArrayList<>();

    public static final AtomicLong idGenerator = new AtomicLong(0);

    @PostConstruct
    void postConstruct() {
        cars = new CopyOnWriteArrayList<>();
        loadInitData();
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Car saveCar(Car car) {
        Car saved = new Car(idGenerator.incrementAndGet(),
                            car.licensePlate(),
                            car.manufacturer(),
                            car.model());
        cars.add(saved);
        return saved;
    }

    private void loadInitData() {
        Car fiat = new Car(idGenerator.incrementAndGet(),
                           "ABC123",
                           "FIAT",
                           "500");

        Car abarth = new Car(idGenerator.incrementAndGet(),
                             "XYZ789",
                             "ABARTH",
                             "500 RS");

        cars.add(fiat);
        cars.add(abarth);
    }

}
