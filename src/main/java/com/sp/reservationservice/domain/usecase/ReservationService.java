package com.sp.reservationservice.domain.usecase;

import com.sp.reservationservice.domain.api.IAuthenticationServicePort;
import com.sp.reservationservice.domain.api.INotificationServicePort;
import com.sp.reservationservice.domain.api.IReservationServicePort;
import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.domain.exception.ConflictException;
import com.sp.reservationservice.domain.exception.DomainException;
import com.sp.reservationservice.domain.exception.ForbiddenException;
import com.sp.reservationservice.domain.exception.NotFoundException;
import com.sp.reservationservice.domain.model.*;
import com.sp.reservationservice.domain.spi.ICourtPersistencePort;
import com.sp.reservationservice.domain.spi.IReservationPersistencePort;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@AllArgsConstructor
public class ReservationService implements IReservationServicePort {

    private final IReservationPersistencePort reservationPersistencePort;
    private final IAuthenticationServicePort authenticationServicePort;
    private final ICourtPersistencePort courtPersistencePort;
    private final INotificationServicePort notificationServicePort;
    @Override
    public void createReservation(Reservation reservation) {
        Long userId = authenticationServicePort.getCurrentUserId();
        validateRole(Role.CLIENT, DomainConstants.ONLY_CLIENT_CAN_CREATE_RESERVATION);
        getActiveCourt(reservation.getCourtId());
        if (reservationPersistencePort.findByDateAndStartTimeAndCourtId(reservation.getDate(),reservation.getStartTime(), reservation.getCourtId()).isPresent()) {
            throw new ConflictException(DomainConstants.COURT_ALREADY_RESERVED);
        }
        if (reservation.getStartTime().getMinute() != 0) {
            throw new DomainException(DomainConstants.RESERVATIONS_MUST_START_ON_THE_HOUR);
        }
        if (reservation.getStartTime().getSecond() != 0) {
            throw new DomainException(DomainConstants.RESERVATIONS_MUST_START_ON_THE_HOUR);
        }
        List<Reservation> userReservations = reservationPersistencePort.findAllByUserId(userId);
        for (Reservation r : userReservations) {
            if (r.getDate().equals(reservation.getDate()) && r.getStartTime().equals(reservation.getStartTime())) {
                throw new ConflictException(DomainConstants.USER_ALREADY_HAS_RESERVATION_AT_THIS_TIME);
            }
        }

        reservation.setUserId(userId);
        reservation.setStatus(ReservationStatus.PENDING);
        notificationServicePort.reservationCreated(authenticationServicePort.getCurrentUserEmail());
        reservationPersistencePort.saveReservation(reservation);
    }

    @Override
    public void updateReservationStatus(Long reservationId, ReservationStatus status) {
       validateRole(Role.ADMIN, DomainConstants.ONLY_ADMIN_CAN_UPDATE_RESERVATION_STATUS);
        Reservation reservation = reservationPersistencePort.findById(reservationId)
                .orElseThrow(() -> new NotFoundException(DomainConstants.MSG_RESERVATION_NOT_FOUND + reservationId));

        validateStatusTransition(reservation.getStatus(), status);
        reservation.setStatus(status);
        reservationPersistencePort.saveReservation(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        validateRole(Role.CLIENT, DomainConstants.ONLY_CLIENT_CAN_CANCEL_RESERVATION);
        Reservation reservation = reservationPersistencePort.findById(reservationId)
                .orElseThrow(() -> new NotFoundException(DomainConstants.MSG_RESERVATION_NOT_FOUND + reservationId));
        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new ConflictException(DomainConstants.RESERVATION_ALREADY_CANCELLED);
        }
        if (reservation.getStatus() == ReservationStatus.COMPLETED) {
            throw new ConflictException(DomainConstants.RESERVATION_ALREADY_COMPLETED);
        }
        if (!reservation.getUserId().equals(authenticationServicePort.getCurrentUserId())) {
            throw new ForbiddenException(DomainConstants.ONLY_OWNER_CAN_CANCEL_RESERVATION);
        }

        notificationServicePort.reservationCanceled(authenticationServicePort.getCurrentUserEmail());
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationPersistencePort.saveReservation(reservation);
    }
    @Override
    public PageModel<Reservation> getReservations(Long userId, String status, int page, int size) {
            validateRole(Role.CLIENT, DomainConstants.ONLY_CLIENT_CAN_GET_RESERVATIONS);
            if (StringUtils.isNotBlank(status)) {
                return  reservationPersistencePort.findAllByUserIdAndStatus(userId, status, page, size);
            }
            return reservationPersistencePort.findAllByUserId(userId, page, size);
    }

    private void validateRole(Role requiredRole, String errorMessage) {
        Role role = authenticationServicePort.getCurrentUserRole();
        if (role != requiredRole) {
            throw new ForbiddenException(errorMessage);
        }
    }
    private void getActiveCourt(Long id){
        Court court = courtPersistencePort.findCourtById(id)
                .orElseThrow(() -> new NotFoundException(DomainConstants.MSG_COURT_NOT_FOUND + id));
        if (!court.getActive()){
            throw new ConflictException(DomainConstants.COURT_IS_DISABLED);
        }
    }
    private void validateStatusTransition(ReservationStatus currentStatus, ReservationStatus newStatus) {
        if (currentStatus != ReservationStatus.PENDING && newStatus == ReservationStatus.CONFIRMED) {
            throw new ConflictException(DomainConstants.ONLY_PENDING_RESERVATIONS_CAN_BE_CONFIRMED);
        }
        if (currentStatus != ReservationStatus.CONFIRMED && newStatus == ReservationStatus.COMPLETED) {
            throw new ConflictException(DomainConstants.ONLY_CONFIRMED_RESERVATIONS_CAN_BE_COMPLETED);
        }
        if (newStatus == ReservationStatus.CANCELLED) {
            throw new ConflictException(DomainConstants.ADMIN_CANNOT_CANCEL_RESERVATION);
        }
        if (newStatus == ReservationStatus.PENDING) {
            throw new ConflictException(DomainConstants.CANNOT_REVERT_TO_PENDING);
        }
        if (currentStatus == ReservationStatus.COMPLETED) {
            throw new ConflictException(DomainConstants.RESERVATION_ALREADY_COMPLETED);
        }
        if (currentStatus == ReservationStatus.CANCELLED) {
            throw new ConflictException(DomainConstants.RESERVATION_ALREADY_CANCELLED);
        }
    }
}
