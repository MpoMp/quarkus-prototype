package dev.inventorysrvc.controller;

import dev.inventorysrvc.persistence.Car;
import org.eclipse.microprofile.graphql.Type;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Type
@Mapper // no CDI because of https://github.com/quarkusio/quarkus/issues/39392, where the workaround won't work either
interface CarMapper {
    CarView map(Car car);

    Car map(CarView car);
}
