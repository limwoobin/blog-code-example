package com.example.oopexample.ocp.service.impl;

import com.example.oopexample.ocp.dto.ReservationDTO;
import com.example.oopexample.ocp.dto.ReservationRequest;
import com.example.oopexample.ocp.service.AirlineReservation;
import org.springframework.stereotype.Component;

@Component
public class TwayReservation implements AirlineReservation {
    @Override
    public ReservationDTO reserve(ReservationRequest request) {
        return null;
    }

    @Override
    public ReservationDTO cancel(ReservationRequest request) {
        return null;
    }
}
