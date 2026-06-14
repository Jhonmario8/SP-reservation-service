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
    private Boolean available;

    public Court(Long id, String name, CourtType type, Integer capacity, Double hourlyRate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.hourlyRate = hourlyRate;
        this.available = true;
    }
}
