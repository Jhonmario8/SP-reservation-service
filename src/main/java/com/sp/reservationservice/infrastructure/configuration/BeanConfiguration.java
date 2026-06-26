package com.sp.reservationservice.infrastructure.configuration;

import com.sp.reservationservice.domain.api.IAuthenticationServicePort;
import com.sp.reservationservice.domain.api.ICourtServicePort;
import com.sp.reservationservice.domain.api.INotificationServicePort;
import com.sp.reservationservice.domain.api.IReservationServicePort;
import com.sp.reservationservice.domain.spi.ICourtPersistencePort;
import com.sp.reservationservice.domain.spi.ICourtTypePersistencePort;
import com.sp.reservationservice.domain.spi.IReservationPersistencePort;
import com.sp.reservationservice.domain.usecase.CourtService;
import com.sp.reservationservice.domain.usecase.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfiguration {

    private final ICourtPersistencePort courtPersistencePort;
    private final IAuthenticationServicePort authenticationServicePort;
    private final IReservationPersistencePort reservationPersistencePort;
    private final ICourtTypePersistencePort courtTypePersistencePort;
    private final INotificationServicePort notificationServicePort;

    @Bean
    public ICourtServicePort courtServicePort() {
        return new CourtService(courtPersistencePort, authenticationServicePort, courtTypePersistencePort);
    }

    @Bean
    public IReservationServicePort reservationServicePort() {
        return new ReservationService(reservationPersistencePort , authenticationServicePort, courtPersistencePort, notificationServicePort);
    }
}
