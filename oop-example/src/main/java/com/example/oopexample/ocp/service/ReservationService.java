package com.example.oopexample.ocp.service;

import com.example.oopexample.ocp.dto.ReservationDTO;
import com.example.oopexample.ocp.dto.ReservationRequest;

public interface ReservationService {
    public ReservationDTO reservation(ReservationRequest request);
}
