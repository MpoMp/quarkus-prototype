package dev.reservationsrvc.biz.reservation;

import dev.reservationsrvc.persistence.reservation.entity.Reservation;
import dev.reservationsrvc.util.OptionalUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
        uses = OptionalUtils.class)
interface ReservationMapper {
    default ReservationDto mapToDto(Reservation reservation){
        return new ReservationDto(reservation.carId(),
                                  reservation.startDate(),
                                  reservation.endDate(),
                                  reservation.userId());
    }

    default Reservation mapFromDto(ReservationDto dto) {
        return new Reservation(dto.carId(), dto.startDate(), dto.endDate(), dto.userId());
    }
}
