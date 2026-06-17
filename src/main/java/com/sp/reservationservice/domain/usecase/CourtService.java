package com.sp.reservationservice.domain.usecase;

import com.sp.reservationservice.domain.api.IAuthenticationServicePort;
import com.sp.reservationservice.domain.api.ICourtServicePort;
import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.domain.exception.ConflictException;
import com.sp.reservationservice.domain.exception.ForbiddenException;
import com.sp.reservationservice.domain.exception.NotFoundException;
import com.sp.reservationservice.domain.model.Court;
import com.sp.reservationservice.domain.model.PageModel;
import com.sp.reservationservice.domain.model.Role;
import com.sp.reservationservice.domain.spi.ICourtPersistencePort;
import com.sp.reservationservice.domain.spi.ICourtTypePersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourtService implements ICourtServicePort {

    private final ICourtPersistencePort courtPersistencePort;
    private final IAuthenticationServicePort authenticationServicePort;
    private final ICourtTypePersistencePort courtTypePersistencePort;

    @Override
    public void createCourt(Court court) {
       validateRole(Role.ADMIN, DomainConstants.ONLY_ADMIN_CAN_CREATE_COURT);
        Court existingCourt = courtPersistencePort.findCourtByName(court.getName()).orElse(null);
        if (existingCourt != null) {
            throw new ConflictException(DomainConstants.MSG_COURT_ALREADY_EXISTS + court.getName());
        }
        courtPersistencePort.saveCourt(court);
    }
    @Override
    public void updateCourt(Court court) {
        validateRole(Role.ADMIN, DomainConstants.ONLY_ADMIN_CAN_UPDATE_COURT);
        getActiveCourt(court.getId());
        courtPersistencePort.saveCourt(court);
    }
    @Override
    public void disableCourt(Long id){
       validateRole(Role.ADMIN, DomainConstants.ONLY_ADMIN_CAN_DISABLE_COURT);
        Court court = getActiveCourt(id);
        court.setActive(!court.getActive());
        courtPersistencePort.saveCourt(court);
    }

    @Override
    public PageModel<Court> getCourts(Boolean active, Long courtTypeId, int page, int size) {
       validateRole(Role.CLIENT,DomainConstants.ONLY_CLIENT_CAN_GET_COURTS);
        if (courtTypeId != null) {
            courtTypePersistencePort.findCourtTypeById(courtTypeId)
                .orElseThrow(() -> new NotFoundException(DomainConstants.MSG_COURT_TYPE_NOT_FOUND + courtTypeId));

            return courtPersistencePort.findCourtsByActiveAndCourtTypeId(active, courtTypeId, page, size);
        }
        return courtPersistencePort.findCourtsByActive(active, page, size);
    }

    private Court getActiveCourt(Long id){
        Court court = courtPersistencePort.findCourtById(id)
                .orElseThrow(() -> new NotFoundException(DomainConstants.MSG_COURT_NOT_FOUND + id));
        if (!court.getActive()){
            throw new ConflictException(DomainConstants.COURT_IS_DISABLED);
        }
        return court;
    }

    private void validateRole(Role requiredRole, String errorMessage) {
        Role role = authenticationServicePort.getCurrentUserRole();
        if (role != requiredRole) {
            throw new ForbiddenException(errorMessage);
        }
    }
}
