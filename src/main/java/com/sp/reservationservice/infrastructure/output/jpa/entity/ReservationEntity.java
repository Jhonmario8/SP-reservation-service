package com.sp.reservationservice.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long courtId;
    private LocalDate date;
    private LocalTime startTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservataion_statuses_id", nullable = false)
    private ReservationStatusEntity reservationStatusEntity;
}
