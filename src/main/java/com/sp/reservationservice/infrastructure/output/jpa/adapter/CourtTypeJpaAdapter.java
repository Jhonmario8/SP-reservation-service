package com.sp.reservationservice.infrastructure.output.jpa.adapter;

import com.sp.reservationservice.domain.model.CourtType;
import com.sp.reservationservice.domain.spi.ICourtTypePersistencePort;
import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtTypeEntity;
import com.sp.reservationservice.infrastructure.output.jpa.repository.ICourtTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourtTypeJpaAdapter implements ICourtTypePersistencePort {

    private final ICourtTypeRepository courtTypeRepository;


    @Override
    public Optional<CourtType> findCourtTypeById(Long id) {
        return courtTypeRepository.findById(id)
                .map(CourtTypeEntity::getName);
    }
}
