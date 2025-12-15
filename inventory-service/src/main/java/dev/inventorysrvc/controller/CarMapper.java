package dev.inventorysrvc.controller;

import dev.inventorysrvc.persistence.Car;
import org.eclipse.microprofile.graphql.Type;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Type
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
interface CarMapper {
    CarView map(Car car);

    Car map(CarView car);
}
