package org.example.ormbulkinsert.bulk_Insert;

import org.example.ormbulkinsert.application.TeamBatchRepository;
import org.example.ormbulkinsert.domain.Project;
import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.fixture.LocationFixture;
import org.example.ormbulkinsert.fixture.ProjectFixture;
import org.example.ormbulkinsert.fixture.TeamFixture;
import org.example.ormbulkinsert.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class BatchInsertTest {

  @Autowired
  private TeamBatchRepository teamBatchRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Test
  void test() {
    // given
    Team team = TeamFixture.TEAM("team-1");
    team.addLocation(LocationFixture.LOCATION_GURO());
    team.addProject(ProjectFixture.PROJECT_1());

    Team team2 = TeamFixture.TEAM("team-2");
    team2.addLocation(LocationFixture.LOCATION_GANGNAM());
    team2.addProject(ProjectFixture.PROJECT_2());
    team2.addProject(ProjectFixture.PROJECT_3());

    Team team3 = TeamFixture.TEAM("team-3");
    team3.addLocation(LocationFixture.LOCATION_GURO());
    team3.addProject(ProjectFixture.PROJECT_4());
    team3.addProject(ProjectFixture.PROJECT_5());
    team3.addProject(ProjectFixture.PROJECT_6());

    // then
    teamBatchRepository.saveAll(List.of(team, team2, team3));

    List<Team> teams = teamRepository.findAllWithProjects();

    List<Project> projects = teams.stream()
      .map(Team::getProjects)
      .flatMap(List::stream)
      .collect(Collectors.toList());

    assertThat(teams).hasSize(3);
    assertThat(projects).hasSize(6);

    assertAll(
      () -> assertThat(teams.get(0).getId()).isEqualTo(projects.get(0).getTeam().getId()),
      () -> assertThat(teams.get(1).getId()).isEqualTo(projects.get(1).getTeam().getId()),
      () -> assertThat(teams.get(1).getId()).isEqualTo(projects.get(2).getTeam().getId()),
      () -> assertThat(teams.get(2).getId()).isEqualTo(projects.get(3).getTeam().getId()),
      () -> assertThat(teams.get(2).getId()).isEqualTo(projects.get(3).getTeam().getId()),
      () -> assertThat(teams.get(2).getId()).isEqualTo(projects.get(3).getTeam().getId())
    );
  }
}
