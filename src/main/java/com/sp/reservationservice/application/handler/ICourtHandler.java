package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.CourtDTO;

public interface ICourtHandler {

    void createCourt(CourtDTO courtDTO);
    void updateCourt(Long courtId, CourtDTO courtDTO);
    void disableCourt(Long courtId);
}
