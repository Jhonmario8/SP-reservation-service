package com.sp.reservationservice.domain.api;

import com.sp.reservationservice.domain.model.Role;

public interface IAuthenticationServicePort {
    Long getCurrentUserId();
    Role getCurrentUserRole();
    String getCurrentUserEmail();
}
