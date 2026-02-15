package dev.inventorysrvc.controller;

import dev.inventorysrvc.persistence.Car;
import org.eclipse.microprofile.graphql.Type;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Type
//@Mapper // no CDI because of https://github.com/quarkusio/quarkus/issues/39392, where the workaround won't work either
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CarMapper {

    //CarMapper INSTANCE = Mappers.getMapper(CarMapper.class );

    CarView map(Car car);

    Car map(CarView car);
}
