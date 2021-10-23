package com.example.jpaexample.domain.controller;

import com.example.jpaexample.domain.service.TeamExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/team/example")
public class TeamExampleApi {
    private final TeamExampleService teamExampleService;

    @GetMapping("")
    public ResponseEntity<?> saveTest() {
        teamExampleService.getTeam(1L);
        log.info("### controller end");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
