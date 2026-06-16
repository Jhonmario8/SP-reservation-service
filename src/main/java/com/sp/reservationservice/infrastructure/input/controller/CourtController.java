package com.sp.reservationservice.infrastructure.input.controller;

import com.sp.reservationservice.application.dto.CourtDTO;
import com.sp.reservationservice.application.dto.PageResponseDTO;
import com.sp.reservationservice.application.handler.ICourtHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courts")
@AllArgsConstructor
public class CourtController {

    private final ICourtHandler courtHandler;

    @PostMapping()
    public ResponseEntity<Void> createCourt(@RequestBody CourtDTO courtDTO) {
        courtHandler.createCourt(courtDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{courtId}")
    public ResponseEntity<Void> updateCourt(@PathVariable Long courtId, @RequestBody CourtDTO courtDTO) {
        courtHandler.updateCourt(courtId, courtDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{courtId}")
    public ResponseEntity<Void> disableCourt(@PathVariable Long courtId) {
        courtHandler.disableCourt(courtId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<CourtDTO>> getCourts(
                                               @RequestParam(required = false) Long courtTypeId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(courtHandler.getCourts(true, courtTypeId, page, size), HttpStatus.OK);
    }
}
