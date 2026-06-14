package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.CourtDTO;
import com.sp.reservationservice.application.mapper.ICourtMapper;
import com.sp.reservationservice.domain.api.ICourtServicePort;
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
}
