package org.example.ormbulkinsert.fixture;

import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.domain.TeamStatus;

public class TeamFixture {

  public static Team TEAM(String name) {
    return Team.builder()
      .name(name)
      .status(TeamStatus.ACTIVE)
      .build();
  }
}
