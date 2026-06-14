package com.sp.reservationservice.infrastructure.output.jpa.mapper;

import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICourtEntityMapper {

    @Mapping(target = "courtTypeEntity.name", source = "type")
    CourtEntity toEntity(Court court);

    @Mapping(target = "type", source = "courtTypeEntity.name")
    Court toDomain(CourtEntity courtEntity);


}
