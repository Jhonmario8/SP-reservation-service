package com.sp.reservationservice.infrastructure.output.jpa.mapper;

import com.sp.reservationservice.domain.model.PageModel;
import com.sp.reservationservice.domain.model.Reservation;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IReservationEntityMapper {

    @Mapping(target = "status", source = "reservationStatusEntity.name")
    Reservation toDomain(ReservationEntity reservationEntity);

    @Mapping(target = "reservationStatusEntity.name", source = "status")
    ReservationEntity toEntity(Reservation reservation);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "pageNumber", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "totalPages", source = "totalPages")
    PageModel<Reservation> toPageModel(Page<ReservationEntity> pageResult);

}
