package com.example.oopexample.ocp.dto;

import com.example.oopexample.ocp.AirlineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private String name;

    private AirlineType airlineType;

    private long flightTimetableId;

}
