package com.example.oopexample.ocp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class XXController {
    private final XXService xxService;

    @GetMapping("/")
    public ResponseEntity<String> asd() {
        Object data = xxService.asd();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
