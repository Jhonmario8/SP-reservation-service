package com.sp.reservationservice.infrastructure.output.jpa.adapter;


import com.sp.reservationservice.domain.exception.NotFoundException;
import com.sp.reservationservice.domain.model.Reservation;
import com.sp.reservationservice.domain.spi.IReservationPersistencePort;
import com.sp.reservationservice.infrastructure.constants.InfrastructureConstants;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationEntity;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationStatusEntity;
import com.sp.reservationservice.infrastructure.output.jpa.mapper.IReservationEntityMapper;
import com.sp.reservationservice.infrastructure.output.jpa.repository.IReservationRepository;
import com.sp.reservationservice.infrastructure.output.jpa.repository.IReservationStatusesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationJpaAdapter implements IReservationPersistencePort {

    private final IReservationRepository reservationRepository;
    private final IReservationEntityMapper reservationEntityMapper;
    private final IReservationStatusesRepository reservationStatusesRepository;

    @Override
    public void saveReservation(Reservation reservation) {
        ReservationStatusEntity status = reservationStatusesRepository.findByName(reservation.getStatus())
                .orElseThrow(() -> new NotFoundException(InfrastructureConstants.MSG_STATUS_NOT_FOUD + reservation.getStatus()));

        ReservationEntity reservationEntity = reservationEntityMapper.toEntity(reservation);
        reservationEntity.setReservationStatusEntity(status);
        reservationRepository.save(reservationEntity);
    }

    @Override
    public Optional<Reservation> findByDateAndStartTimeAndCourtId(LocalDate date, LocalTime time, Long courtId) {
        return reservationRepository.findByDateAndStartTimeAndCourtId(date,time, courtId)
                .map(reservationEntityMapper::toDomain);
    }

}
