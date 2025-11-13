package dev.reservationsrvc.biz.inventory;

import dev.inventory.integration.CarView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
interface CarMapper {
    CarDto mapToDto(CarView car);
}
