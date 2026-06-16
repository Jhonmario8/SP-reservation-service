package com.sp.reservationservice.infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courts")
public class CourtEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;
    private Double hourlyRate;
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "court_type_id", nullable = false)
    private CourtTypeEntity courtTypeEntity;
}
