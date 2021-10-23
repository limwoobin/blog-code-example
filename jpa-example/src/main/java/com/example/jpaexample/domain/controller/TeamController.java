package com.example.jpaexample.domain.controller;

import com.example.jpaexample.domain.dto.TeamDto;
import com.example.jpaexample.domain.entity.Team;
import com.example.jpaexample.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @PostMapping("")
    public ResponseEntity<?> saveTest() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName("test");

        teamService.save(teamDto);
        log.info("### controller end");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public String findTest() {

        Team team = teamService.findTeam(1L);
        log.info("## team  {}" , team.getName());

        return "ok";
    }

    @GetMapping("/find/cash")
    public String cashTest() {

//        TeamDto teamDto = new TeamDto();
//        teamDto.setName("test");
        teamService.cashTest(1L);

        return "ok";
    }

    @GetMapping("/find/cash/v2")
    public String cashTest2() {

        TeamDto teamDto = new TeamDto();
        teamDto.setName("test");
//        teamService.cashTest2(teamDto);
        teamService.cashTest3(1L);

        return "ok";
    }
}
