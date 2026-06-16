package com.sp.reservationservice.infrastructure.output.jpa.mapper;

import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.domain.model.PageModel;
import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICourtEntityMapper {

    @Mapping(target = "courtTypeEntity.name", source = "type")
    CourtEntity toEntity(Court court);

    @Mapping(target = "type", source = "courtTypeEntity.name")
    Court toDomain(CourtEntity courtEntity);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "pageNumber", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "totalPages", source = "totalPages")
    PageModel<Court> toPageModel(Page<CourtEntity> pageResult);

}
