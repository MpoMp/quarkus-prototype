package dev.reservationsrvc.biz.inventory;

import dev.reservationsrvc.integration.inventory.Car;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface CarManager {
    List<CarDto> findAllCars();

}
