package com.example.oopexample.ocp.service;

import com.example.oopexample.ocp.dto.ReservationDTO;
import com.example.oopexample.ocp.dto.ReservationRequest;

public interface AirlineReservation {
    ReservationDTO reserve(ReservationRequest request);
    ReservationDTO cancel(ReservationRequest request);
}
