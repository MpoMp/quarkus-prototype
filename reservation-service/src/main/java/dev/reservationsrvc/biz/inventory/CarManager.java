package dev.reservationsrvc.biz.inventory;

import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public interface CarManager {
    List<CarDto> findAllCars();

}
