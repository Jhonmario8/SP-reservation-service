package com.sp.reservationservice.domain.spi;

import com.sp.reservationservice.domain.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface IReservationPersistencePort {
    void saveReservation(Reservation reservation);
    Optional<Reservation> findByDateAndStartTimeAndCourtId(LocalDate date, LocalTime time, Long courtId);
    Optional<Reservation> findById(Long reservationId);
}
