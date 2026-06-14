package com.sp.reservationservice.domain.spi;

import com.sp.reservationservice.domain.model.Court;

public interface ICourtPersistencePort {

    void saveCourt(Court court);

}
