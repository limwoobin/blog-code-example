package com.example.oopexample.ocp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class XXFactory {
    private final XXAServiceImpl xxaService;
    private final XXBServiceImpl xxbService;

    public XXService getXX() {

        return null;
    }
}
