package com.sp.reservationservice.domain.usecase;

import com.sp.reservationservice.domain.api.IAuthenticationServicePort;
import com.sp.reservationservice.domain.api.ICourtServicePort;
import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.domain.exception.ForbiddenException;
import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.domain.model.Role;
import com.sp.reservationservice.domain.spi.ICourtPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourtService implements ICourtServicePort {

    private final ICourtPersistencePort courtPersistencePort;
    private final IAuthenticationServicePort authenticationServicePort;

    @Override
    public void createCourt(Court court) {
        Role role = authenticationServicePort.getCurrentUserRole();
        if (role != Role.ADMIN) {
            throw new ForbiddenException(DomainConstants.ONLY_ADMIN_CAN_CREATE_COURT);
        }
        courtPersistencePort.saveCourt(court);
    }
}
