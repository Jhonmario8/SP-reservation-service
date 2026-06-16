package com.sp.reservationservice.domain.spi;

import com.sp.reservationservice.domain.model.Court;

import java.util.Optional;

public interface ICourtPersistencePort {

    void saveCourt(Court court);
    Optional<Court> findCourtByName(String name);
    Optional<Court> findCourtById(Long id);
}
