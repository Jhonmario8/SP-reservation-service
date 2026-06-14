package com.sp.reservationservice.infrastructure.output.security.adapter;

import com.sp.reservationservice.domain.api.IAuthenticationServicePort;
import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.domain.model.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthenticationAdapter implements IAuthenticationServicePort {


    @Override
    public Long getCurrentUserId() {
        Map<String, Object> claims = getClaimsFromAuthentication();
        if (claims == null ) {
            return null;
        }
        return Long.valueOf(claims.get(DomainConstants.USER_ID_CLAIM).toString());
    }

    @Override
    public Role getCurrentUserRole() {
        Map<String, Object> claims = getClaimsFromAuthentication();
        if (claims == null ) {
            return null;
        }
        String roleName = claims.get(DomainConstants.ROLE_NAME_CLAIM).toString();
        try {
            return Role.valueOf(roleName);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    private static Map<String, Object> getClaimsFromAuthentication() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (Map<String, Object>) authentication.getDetails();
    }
}
