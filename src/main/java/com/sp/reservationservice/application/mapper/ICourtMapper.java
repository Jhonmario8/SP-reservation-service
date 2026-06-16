package com.sp.reservationservice.application.mapper;

import com.sp.reservationservice.application.dto.CourtDTO;
import com.sp.reservationservice.domain.model.Court;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICourtMapper {

    Court toDomain(CourtDTO courtDTO);


}
