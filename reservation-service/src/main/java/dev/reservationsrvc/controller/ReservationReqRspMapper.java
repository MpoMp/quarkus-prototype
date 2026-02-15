package dev.reservationsrvc.controller;

import dev.reservationsrvc.biz.reservation.ReservationDto;
import dev.reservationsrvc.util.OptionalUtils;
import dev.reservationsrvc.util.RelaxedMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
        uses = OptionalUtils.class,
       config = RelaxedMapperConfig.class
)
interface ReservationReqRspMapper {
   default ReservationDto mapToDto(ReservationReq reservation, String userId){
       return new ReservationDto(reservation.carId(),
                                 reservation.startDate(),
                                 reservation.endDate(),
                                 userId);
   }

    ReservationRsp mapFromDto(ReservationDto reservation);
}
