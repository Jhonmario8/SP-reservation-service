package com.sp.reservationservice.infrastructure.output.jpa.repository;


import com.sp.reservationservice.domain.model.ReservationStatus;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReservationStatusesRepository extends JpaRepository<ReservationStatusEntity, Long> {
       Optional<ReservationStatusEntity> findByName(ReservationStatus statusName);

}
