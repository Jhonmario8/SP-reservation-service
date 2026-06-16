package com.sp.reservationservice.infrastructure.output.jpa.repository;

import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICourtRepository extends JpaRepository<CourtEntity, Long> {
    Optional<CourtEntity> findByName(String name);

}
