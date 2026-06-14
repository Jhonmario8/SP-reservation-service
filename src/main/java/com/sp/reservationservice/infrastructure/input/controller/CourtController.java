package com.sp.reservationservice.infrastructure.input.controller;

import com.sp.reservationservice.application.dto.CourtDTO;
import com.sp.reservationservice.application.handler.ICourtHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courts")
@AllArgsConstructor
public class CourtController {

    private final ICourtHandler courtHandler;

    @PostMapping()
    public void createCourt(@RequestBody CourtDTO courtDTO) {
        courtHandler.createCourt(courtDTO);
    }

}
