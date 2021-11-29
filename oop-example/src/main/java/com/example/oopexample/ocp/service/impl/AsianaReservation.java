package com.example.oopexample.ocp.service.impl;

import com.example.oopexample.ocp.dto.ReservationDTO;
import com.example.oopexample.ocp.dto.ReservationRequest;
import com.example.oopexample.ocp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsianaReservation implements ReservationService {

    @Override
    public ReservationDTO reservation(ReservationRequest request) {
        return null;
    }
}
