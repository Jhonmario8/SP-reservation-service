package com.sp.reservationservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Court {

    private Long id;
    private String name;
    private CourtType type;
    private Integer capacity;
    private Double hourlyRate;
    private Boolean active = true;

}
