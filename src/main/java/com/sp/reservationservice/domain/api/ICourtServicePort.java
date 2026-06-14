package com.sp.reservationservice.domain.api;

import com.sp.reservationservice.domain.model.Court;

public interface ICourtServicePort {
    void createCourt(Court court);
}
