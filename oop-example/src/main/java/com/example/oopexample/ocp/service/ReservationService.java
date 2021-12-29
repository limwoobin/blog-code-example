package com.example.oopexample.ocp.service;

import com.example.oopexample.ocp.dto.ReservationDTO;
import com.example.oopexample.ocp.dto.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final AirLineReservationFactory airLineReservationFactory;

    public ReservationDTO reservation(ReservationRequest request) {
        AirlineReservation airlineReservation = airLineReservationFactory.createReservationService(request.getAirlineType());
        return airlineReservation.reserve(request);
    }

    public ReservationDTO cancelReservation(ReservationRequest request) {
        AirlineReservation airlineReservation = airLineReservationFactory.createReservationService(request.getAirlineType());
        return airlineReservation.cancel(request);
    }
}
