package com.sp.reservationservice.infrastructure.output.jpa.mapper;

import com.sp.reservationservice.domain.model.Reservation;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IReservationEntityMapper {

    @Mapping(target = "status", source = "reservationStatusEntity.name")
    Reservation toDomain(ReservationEntity reservationEntity);

    @Mapping(target = "reservationStatusEntity.name", source = "status")
    ReservationEntity toEntity(Reservation reservation);

}
