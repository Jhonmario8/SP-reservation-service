package com.sp.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpReservationServiceApplication.class, args);
    }

}
