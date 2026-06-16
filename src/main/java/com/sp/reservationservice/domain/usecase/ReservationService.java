package com.sp.reservationservice.domain.usecase;

import com.sp.reservationservice.domain.api.IAuthenticationServicePort;
import com.sp.reservationservice.domain.api.IReservationServicePort;
import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.domain.exception.ConflictException;
import com.sp.reservationservice.domain.exception.ForbiddenException;
import com.sp.reservationservice.domain.model.Reservation;
import com.sp.reservationservice.domain.model.ReservationStatus;
import com.sp.reservationservice.domain.model.Role;
import com.sp.reservationservice.domain.spi.IReservationPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReservationService implements IReservationServicePort {

    private final IReservationPersistencePort reservationPersistencePort;
    private final IAuthenticationServicePort authenticationServicePort;


    @Override
    public void createReservation(Reservation reservation) {
        Role role = authenticationServicePort.getCurrentUserRole();
        if (role != Role.CLIENT){
            throw new ForbiddenException(DomainConstants.ONLY_CLIENT_CAN_CREATE_RESERVATION);
        }

        if (reservationPersistencePort.findByDateAndStartTimeAndCourtId(reservation.getDate(),reservation.getStartTime(), reservation.getCourtId()).isPresent()) {
            throw new ConflictException(DomainConstants.COURT_ALREADY_RESERVED);
        }

        reservation.setUserId(authenticationServicePort.getCurrentUserId());
        reservation.setStatus(ReservationStatus.PENDING);
        reservationPersistencePort.saveReservation(reservation);

    }

}
