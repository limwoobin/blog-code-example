package com.example.oopexample.ocp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    // 예약 번호
    private long id;
    
    // 예약자 성함
    private String name;
    
    // 좌석 이름(번호)
    private String sheet;

    // 항공사 번호(ID)
    private long airlineId;

    // 출발 시간
    private LocalDateTime departureTime;

    // 도착 시간
    private LocalDateTime arrivalTime;
}
