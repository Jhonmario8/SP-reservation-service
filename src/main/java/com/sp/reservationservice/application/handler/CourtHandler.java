package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.CourtDTO;
import com.sp.reservationservice.application.mapper.ICourtMapper;
import com.sp.reservationservice.domain.api.ICourtServicePort;
import com.sp.reservationservice.domain.model.Court;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourtHandler implements ICourtHandler {

    private final ICourtServicePort courtServicePort;
    private final ICourtMapper courtMapper;

    @Override
    public void createCourt(CourtDTO courtDTO) {
        courtServicePort.createCourt(courtMapper.toDomain(courtDTO));
    }

    @Override
    public void updateCourt(Long courtId,CourtDTO courtDTO) {
        Court domain = courtMapper.toDomain(courtDTO);
        domain.setId(courtId);
        courtServicePort.updateCourt(domain);
    }

    @Override
    public void disableCourt(Long courtId) {
        courtServicePort.disableCourt(courtId);
    }
}
