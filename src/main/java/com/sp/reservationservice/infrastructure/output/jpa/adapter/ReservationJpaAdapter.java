package com.sp.reservationservice.infrastructure.output.jpa.adapter;


import com.sp.reservationservice.domain.exception.NotFoundException;
import com.sp.reservationservice.domain.model.PageModel;
import com.sp.reservationservice.domain.model.Reservation;
import com.sp.reservationservice.domain.model.ReservationStatus;
import com.sp.reservationservice.domain.spi.IReservationPersistencePort;
import com.sp.reservationservice.infrastructure.constants.InfrastructureConstants;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationEntity;
import com.sp.reservationservice.infrastructure.output.jpa.entity.ReservationStatusEntity;
import com.sp.reservationservice.infrastructure.output.jpa.mapper.IReservationEntityMapper;
import com.sp.reservationservice.infrastructure.output.jpa.repository.IReservationRepository;
import com.sp.reservationservice.infrastructure.output.jpa.repository.IReservationStatusesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(reservationEntityMapper::toDomain);
    }

    @Override
    public PageModel<Reservation> findAllByUserId(Long userId, int page, int size) {
        return reservationEntityMapper.toPageModel(reservationRepository.findAllByUserId(userId, PageRequest.of(page, size)));
    }

    @Override
    public PageModel<Reservation> findAllByUserIdAndStatus(Long userId, String status, int page, int size) {
        ReservationStatusEntity reservationStatusEntity = reservationStatusesRepository.findByName(ReservationStatus.valueOf(status))
                .orElseThrow(() -> new NotFoundException(InfrastructureConstants.MSG_STATUS_NOT_FOUD + status));
        return reservationEntityMapper.toPageModel(reservationRepository.findAllByUserIdAndReservationStatusEntity(userId, reservationStatusEntity, PageRequest.of(page, size)));
    }
    @Override
    public List<Reservation> findAllByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId)
                .stream()
                .map(reservationEntityMapper::toDomain)
                .toList();
    }
}
