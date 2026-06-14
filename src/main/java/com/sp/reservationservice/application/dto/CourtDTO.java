package com.sp.reservationservice.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sp.reservationservice.application.constants.ApplicationConstants;
import com.sp.reservationservice.domain.model.CourtType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourtDTO {

    @NotBlank(message = ApplicationConstants.MSG_COURT_NAME_CANNOT_BE_BLANK)
    private String name;
    @NotNull(message = ApplicationConstants.MSG_COURT_TYPE_CANNOT_BE_NULL)
    private CourtType type;
    @NotNull(message = ApplicationConstants.MSG_COURT_CAPACITY_CANNOT_BE_NULL)
    private Integer capacity;
    @NotNull(message = ApplicationConstants.MSG_COURT_HOURLY_RATE_CANNOT_BE_NULL)
    private Double hourlyRate;


}
