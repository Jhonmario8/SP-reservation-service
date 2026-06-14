package com.sp.reservationservice.infrastructure.output.jpa.repository;

import com.sp.reservationservice.domain.model.CourtType;
import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICourtTypeRepository extends JpaRepository<CourtTypeEntity, Long> {

    Optional<CourtTypeEntity> findByName(CourtType name);

}
