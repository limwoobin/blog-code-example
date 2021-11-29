package com.example.oopexample.ocp.controller;

import com.example.oopexample.ocp.AirLineReservationFactory;
import com.example.oopexample.ocp.dto.ReservationDTO;
import com.example.oopexample.ocp.dto.ReservationRequest;
import com.example.oopexample.ocp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final AirLineReservationFactory airLineReservationFactory;

    @PostMapping("/")
    public ResponseEntity<ReservationDTO> getReservation(ReservationRequest request) {
        ReservationService reservationService = airLineReservationFactory.getXX(request.getAirlineType());
        ReservationDTO reservationDTO = reservationService.reservation(request);
        return new ResponseEntity<>(reservationDTO , HttpStatus.OK);
    }

    @PostMapping("/v2")
    public ResponseEntity<ReservationDTO> getReservation2(ReservationRequest request) {
        ReservationService reservationService = airLineReservationFactory.getXX(request.getAirlineType());
        ReservationDTO reservationDTO = reservationService.reservation(request);
        return new ResponseEntity<>(reservationDTO , HttpStatus.OK);
    }
}
