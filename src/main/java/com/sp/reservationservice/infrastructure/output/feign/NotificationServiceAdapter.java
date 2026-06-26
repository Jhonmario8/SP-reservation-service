package com.sp.reservationservice.infrastructure.output.feign;

import com.sp.reservationservice.domain.api.INotificationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceAdapter implements INotificationServicePort {

    private final NotificationClient notificationClient;


    @Override
    public void reservationCreated(String to) {
        notificationClient.reservationCreated(to);
    }

    @Override
    public void reservationCanceled(String to) {
        notificationClient.reservationCanceled(to);
    }
}
