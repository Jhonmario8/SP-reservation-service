package com.sp.reservationservice.infrastructure.input.controller;

import com.sp.reservationservice.application.dto.ReservationRequest;
import com.sp.reservationservice.application.handler.IReservationHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

    private final IReservationHandler reservationHandler;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationRequest request){
        reservationHandler.createReservation(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
