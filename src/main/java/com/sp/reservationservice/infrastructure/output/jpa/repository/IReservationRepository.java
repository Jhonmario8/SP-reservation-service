package com.sp.reservationservice.infrastructure.output.jpa.repository;

import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<ReservationEntity, Long> {

        Optional<ReservationEntity> findByDateAndStartTimeAndCourtId(LocalDate date, LocalTime startTime, Long courtId);
}
