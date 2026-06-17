package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.PageResponseDTO;
import com.sp.reservationservice.application.dto.ReservationRequest;
import com.sp.reservationservice.application.dto.ReservationResponseDTO;

public interface IReservationHandler {
    void createReservation(ReservationRequest request);
    void updateReservationStatus(Long reservationId, String status);
    void cancelReservation(Long reservationId);
    PageResponseDTO<ReservationResponseDTO> getReservations(Long userId, String status, int page, int size);
}
