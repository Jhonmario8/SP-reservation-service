package com.sp.reservationservice.application.mapper;

import com.sp.reservationservice.application.dto.PageResponseDTO;
import com.sp.reservationservice.application.dto.ReservationRequest;
import com.sp.reservationservice.application.dto.ReservationResponseDTO;
import com.sp.reservationservice.domain.model.PageModel;
import com.sp.reservationservice.domain.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IReservationMapper {

    Reservation toDomain(ReservationRequest request);

    PageResponseDTO<ReservationResponseDTO> toPageResponse(PageModel<Reservation> pageModel);
}
