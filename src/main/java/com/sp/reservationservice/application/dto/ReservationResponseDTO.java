package com.sp.reservationservice.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;



@Getter
@Setter
public class ReservationResponseDTO {

    private Long courtId;
    private Long userId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;

    public ReservationResponseDTO() {

    }



    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusHours(1);
    }
}