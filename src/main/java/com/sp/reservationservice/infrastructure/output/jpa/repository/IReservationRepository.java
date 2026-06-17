package com.sp.reservationservice.infrastructure.output.jpa.repository;

import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationEntity;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<ReservationEntity, Long> {

        Optional<ReservationEntity> findByDateAndStartTimeAndCourtId(LocalDate date, LocalTime startTime, Long courtId);
        List<ReservationEntity> findAllByUserId(Long userId);
        Page<ReservationEntity> findAllByUserId(Long useerId, Pageable pageable);
        Page<ReservationEntity> findAllByUserIdAndReservationStatusEntity(Long userId, ReservationStatusEntity reservationStatusEntity, Pageable pageable);
}
