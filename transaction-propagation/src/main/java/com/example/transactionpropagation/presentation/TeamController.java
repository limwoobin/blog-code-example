package com.example.transactionpropagation.presentation;

import com.example.transactionpropagation.application.TeamService;
import com.example.transactionpropagation.application.TeamServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teams")
public class TeamController {

  private final TeamService teamService;
  private final TeamServiceV2 teamServiceV2;

  @PostMapping
  public ResponseEntity<Void> saveTeam(@RequestBody String name) {
    teamService.save(name);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(value = "v2")
  public ResponseEntity<Void> saveTeam2(@RequestBody String name) {
    teamService.save2(name);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(value = "v3")
  public ResponseEntity<Void> saveTeam3(@RequestBody String name) {
    teamService.save3(name);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(value = "v4")
  public ResponseEntity<Void> saveTeam4(@RequestBody String name) {
    teamServiceV2.save(name);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
