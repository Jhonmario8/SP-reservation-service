package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.PageResponseDTO;
import com.sp.reservationservice.application.dto.ReservationRequest;
import com.sp.reservationservice.application.dto.ReservationResponseDTO;
import com.sp.reservationservice.application.mapper.IReservationMapper;
import com.sp.reservationservice.domain.api.IReservationServicePort;
import com.sp.reservationservice.domain.model.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationHandler implements IReservationHandler{

    private final IReservationServicePort reservationServicePort;
    private final IReservationMapper reservationMapper;

    @Override
    public void createReservation(ReservationRequest request) {
        reservationServicePort.createReservation(reservationMapper.toDomain(request));
    }

    @Override
    public void updateReservationStatus(Long reservationId, String status) {
        reservationServicePort.updateReservationStatus(reservationId, ReservationStatus.valueOf(status));
    }

    @Override
    public void cancelReservation(Long reservationId) {
        reservationServicePort.cancelReservation(reservationId);
    }

    @Override
    public PageResponseDTO<ReservationResponseDTO> getReservations(Long userId, String status, int page, int size) {
        return reservationMapper.toPageResponse(reservationServicePort.getReservations(userId, status, page, size));
    }

}
