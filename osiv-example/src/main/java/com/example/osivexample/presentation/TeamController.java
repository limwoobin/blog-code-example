package com.example.osivexample.presentation;

import com.example.osivexample.application.TeamService;
import com.example.osivexample.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teams")
@Slf4j
public class TeamController {
  private final TeamService teamService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<TeamResponse> findTeam(@PathVariable Long id) {
    Team team = teamService.findTeam(id);
    log.info("member size = {}", team.getMembers().size());
    TeamResponse response = new TeamResponse(team);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
