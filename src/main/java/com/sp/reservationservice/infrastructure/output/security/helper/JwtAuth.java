package com.sp.reservationservice.infrastructure.output.security.helper;

import com.sp.reservationservice.domain.constants.DomainConstants;
import com.sp.reservationservice.infrastructure.constants.InfrastructureConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class JwtAuth implements Authentication {

    private final Map<String, Object> claims;
    private final String token;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Object roleName = claims.get(DomainConstants.KEY_ROLE_NAME);
        if (roleName == null) {
            return Collections.emptyList();
        }
        String role = String.valueOf(roleName);
        return List.of( new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return claims;
    }

    @Override
    public Object getPrincipal() {
        return claims.get(DomainConstants.KEY_SUBJECT);
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException(InfrastructureConstants.MSG_AUTHENTICATION_STATE_CHANGE_NOT_SUPPORTED);
    }

    @Override
    public String getName() {
        return (String) claims.get(DomainConstants.KEY_SUBJECT);
    }
}