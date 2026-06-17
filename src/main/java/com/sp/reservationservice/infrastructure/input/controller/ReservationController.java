package com.sp.reservationservice.infrastructure.input.controller;

import com.sp.reservationservice.application.dto.ReservationRequest;
import com.sp.reservationservice.application.handler.IReservationHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

    private final IReservationHandler reservationHandler;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationRequest request) {
        reservationHandler.createReservation(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateReservationStatus(@PathVariable Long id, @RequestParam String status) {
        reservationHandler.updateReservationStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationHandler.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getReservations(@PathVariable Long userId, @RequestParam(required = false) String status,
                                             @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(reservationHandler.getReservations(userId, status, page, size), HttpStatus.OK);
    }
}
