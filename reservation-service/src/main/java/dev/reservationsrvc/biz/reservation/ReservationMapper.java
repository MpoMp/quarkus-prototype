package dev.reservationsrvc.biz.reservation;

import dev.reservationsrvc.persistence.reservation.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
interface ReservationMapper {
    ReservationDto mapToDto(Reservation reservation);
}
