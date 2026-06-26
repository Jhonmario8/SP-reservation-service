package com.sp.reservationservice.infrastructure.output.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", url = "${services.url-notification}")
public interface NotificationClient {

    @PostMapping("/notifications/reservation-created")
    void reservationCreated(String to);

    @PostMapping("/notifications/reservation-canceled")
    void reservationCanceled(String to);

}
