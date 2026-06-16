package com.sp.reservationservice.domain.api;

import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.domain.model.PageModel;

public interface ICourtServicePort {
    void createCourt(Court court);
    void updateCourt(Court court);
    void disableCourt(Long courtId);
    PageModel<Court> getCourts(Boolean active, Long courtTypeId, int page, int size);
}
