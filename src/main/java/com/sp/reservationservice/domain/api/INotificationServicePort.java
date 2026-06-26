package com.sp.reservationservice.domain.api;

public interface INotificationServicePort {
    void reservationCreated(String to);
    void reservationCanceled(String to);
}
