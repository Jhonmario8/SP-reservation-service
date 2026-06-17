package com.sp.reservationservice.application.handler;

import com.sp.reservationservice.application.dto.ReservationRequest;

public interface IReservationHandler {
    void createReservation(ReservationRequest request);
    void updateReservationStatus(Long reservationId, String status);
    void cancelReservation(Long reservationId);
}
