package com.example.osivexample.presentation;

import com.example.osivexample.application.TeamService;
import com.example.osivexample.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GqlTeamController {
  private final TeamService teamService;

  @QueryMapping
  public TeamResponse teamById(@Argument Long id) {
    Team team = teamService.findTeam(id);
    log.info("member size = {}", team.getMembers().size());

    return new TeamResponse(team);
  }
}
