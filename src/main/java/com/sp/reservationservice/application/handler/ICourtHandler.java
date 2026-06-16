package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.CourtDTO;
import com.sp.reservationservice.application.dto.PageResponseDTO;

public interface ICourtHandler {

    void createCourt(CourtDTO courtDTO);
    void updateCourt(Long courtId, CourtDTO courtDTO);
    void disableCourt(Long courtId);
    PageResponseDTO<CourtDTO> getCourts(Boolean active, Long courtTypeId, int page, int size);
}
