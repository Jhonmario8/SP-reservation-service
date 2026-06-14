package com.sp.reservationservice.infrastructure.output.jpa.adapter;

import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.domain.exception.NotFoundException;
import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.domain.spi.ICourtPersistencePort;
import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtEntity;
import com.sp.reservationservice.infrastructure.output.jpa.entity.CourtTypeEntity;
import com.sp.reservationservice.infrastructure.output.jpa.mapper.ICourtEntityMapper;
import com.sp.reservationservice.infrastructure.output.jpa.repository.ICourtRepository;
import com.sp.reservationservice.infrastructure.output.jpa.repository.ICourtTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourtJpaAdapter implements ICourtPersistencePort {

    private final ICourtRepository courtRepository;
    private final ICourtEntityMapper courtEntityMapper;
    private final ICourtTypeRepository courtTypeRepository;

    @Override
    public void saveCourt(Court court) {
        CourtTypeEntity courtTypeEntity = courtTypeRepository.findByName(court.getType())
                .orElseThrow(() -> new NotFoundException(DomainConstants.MSG_COURT_NOT_FOUND + court.getType().name()));
        CourtEntity courtEntity = courtEntityMapper.toEntity(court);
        courtEntity.setCourtTypeEntity(courtTypeEntity);
        courtRepository.save(courtEntity);
    }
}
