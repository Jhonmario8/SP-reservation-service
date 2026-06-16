package com.sp.reservationservice.infrastructure.output.jpa.repository;

import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICourtRepository extends JpaRepository<CourtEntity, Long> {
    Optional<CourtEntity> findByName(String name);
    Page<CourtEntity> findByActive( Boolean active, Pageable pageable);
    Page<CourtEntity> findByActiveAndCourtTypeEntity_Id( Boolean active, Long courtTypeId, Pageable pageable);
}
