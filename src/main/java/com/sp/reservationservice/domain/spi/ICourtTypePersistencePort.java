package com.sp.reservationservice.domain.spi;

import com.sp.reservationservice.domain.model.CourtType;

import java.util.Optional;

public interface ICourtTypePersistencePort {
    Optional<CourtType> findCourtTypeById(Long id);
}
