package com.sp.reservationservice.domain.api;

import java.util.Map;

public interface ITokenServicePort {
    Map<String, Object> validateToken(String token);
}
