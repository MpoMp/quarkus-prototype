package dev.reservationsrvc.biz.inventory;

import dev.reservationsrvc.integration.inventory.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
interface CarMapper {
    CarDto mapToDto(Car car);
}
