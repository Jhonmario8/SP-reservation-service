package com.sp.reservationservice.application.dto;

import com.sp.reservationservice.application.constants.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {

    @NotNull(message = ApplicationConstants.MSG_COURT_ID_CANNOT_BE_NULL)
    private Long courtId;
    @NotNull(message = ApplicationConstants.MSG_DATE_CANNOT_BE_NULL)
    private LocalDate date;
    @NotNull(message = ApplicationConstants.MSG_START_TIME_CANNOT_BE_NULL)
    private LocalTime startTime;

}
