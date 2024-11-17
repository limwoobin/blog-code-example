package org.example.ormbulkinsert.fixture;

import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.domain.TeamStatus;

public class TeamFixture {

  public static Team TEAM_1() {
    return Team.builder()
      .name("team")
      .status(TeamStatus.ACTIVE)
      .build();
  }

  public static Team TEAM_2() {
    return Team.builder()
      .name("team-2")
      .status(TeamStatus.ACTIVE)
      .build();
  }
}
