package com.sp.reservationservice.domain.api;


import com.sp.reservationservice.domain.model.Reservation;

public interface IReservationServicePort {

    void createReservation(Reservation reservation);

}
