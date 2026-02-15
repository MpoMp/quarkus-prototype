package dev.reservationsrvc.biz.reservation;

import dev.reservationsrvc.persistence.reservation.Reservation;
import dev.reservationsrvc.util.OptionalUtils;
import dev.reservationsrvc.util.RelaxedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
        uses = OptionalUtils.class)
interface ReservationMapper {
    ReservationDto mapToDto(Reservation reservation);

    default Reservation mapFromDto(ReservationDto dto) {
        return new Reservation(dto.carId(), dto.startDate(), dto.endDate(), dto.userId());
    }
}
