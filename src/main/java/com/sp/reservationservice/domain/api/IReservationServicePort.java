package com.sp.reservationservice.domain.api;


import com.sp.reservationservice.domain.model.PageModel;
import com.sp.reservationservice.domain.model.Reservation;
import com.sp.reservationservice.domain.model.ReservationStatus;

public interface IReservationServicePort {

    void createReservation(Reservation reservation);

    void updateReservationStatus(Long reservationId, ReservationStatus status);

    void cancelReservation(Long reservationId);

    PageModel<Reservation> getReservations(Long userId, String status, int page, int size);
}
