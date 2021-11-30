package com.example.oopexample.ocp;

import com.example.oopexample.ocp.service.AirlineReservation;
import com.example.oopexample.ocp.service.ReservationService;
import com.example.oopexample.ocp.service.impl.AirSeoulReservation;
import com.example.oopexample.ocp.service.impl.KoreanAirReservation;
import com.example.oopexample.ocp.service.impl.AsianaReservation;
import com.example.oopexample.ocp.service.impl.TwayReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirLineReservationFactory {
    private final KoreanAirReservation koreanAirReservation;
    private final AsianaReservation asianaReservation;
    private final AirSeoulReservation airSeoulReservation;
    private final TwayReservation twayReservation;

    public AirlineReservation createReservationService(AirlineType airlineType) {
        switch (airlineType) {
            case KOREAN_AIR: return koreanAirReservation;
            case ASIANA_AIR: return asianaReservation;
            case AIR_SEOUL: return airSeoulReservation;
            case T_WAY: return twayReservation;
        }

        throw new IllegalArgumentException();
    }
}
