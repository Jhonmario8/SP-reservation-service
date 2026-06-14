package com.sp.reservationservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    private Long id;
    private Long userId;
    private Long courtId;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
}
