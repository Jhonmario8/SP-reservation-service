package com.sp.reservationservice.domain.spi;

import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.domain.model.PageModel;


import java.util.Optional;

public interface ICourtPersistencePort {

    void saveCourt(Court court);
    Optional<Court> findCourtByName(String name);
    Optional<Court> findCourtById(Long id);
    PageModel<Court> findCourtsByActive(Boolean active, int page, int size);
    PageModel<Court> findCourtsByActiveAndCourtTypeId(Boolean active, Long courtTypeId, int page, int size);
}
