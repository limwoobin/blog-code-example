package org.example.ormbulkinsert.bulk_Insert;

import org.example.ormbulkinsert.domain.Location;
import org.example.ormbulkinsert.domain.Project;
import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.fixture.LocationFixture;
import org.example.ormbulkinsert.fixture.ProjectFixture;
import org.example.ormbulkinsert.fixture.TeamFixture;
import org.example.ormbulkinsert.genrator.BulkInsertQueryFactory;
import org.example.ormbulkinsert.repository.TeamJdbcRepository;
import org.example.ormbulkinsert.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BulkInsertTest {

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private TeamJdbcRepository teamJdbcRepository;

  @Test
  void test() throws IllegalAccessException {
    // given
    Team team = TeamFixture.TEAM_1();
    team.addLocation(LocationFixture.LOCATION_GANGNAM());
    team.addProject(ProjectFixture.PROJECT_1());
    team.addProject(ProjectFixture.PROJECT_2());

    Team team2 = TeamFixture.TEAM_2();
    team2.addLocation(LocationFixture.LOCATION_GURO());
    team2.addProject(ProjectFixture.PROJECT_3());
    team2.addProject(ProjectFixture.PROJECT_4());
    team2.addProject(ProjectFixture.PROJECT_5());

    // then
    List<Team> teams = List.of(team, team2);
    teamJdbcRepository.save(teams);

    List<Team> result = teamRepository.findAllWithProjects();
    assertThat(result).hasSize(2);

    Team result1 = result.get(0);
    assertThat(result1.getName()).isEqualTo(team.getName());
    assertThat(result1.getLocation().getAddr()).isEqualTo(team.getLocation().getAddr());
    assertThat(result1.getProjects()).hasSize(2);

    Team result2 = result.get(1);
    assertThat(result2.getName()).isEqualTo(team2.getName());
    assertThat(result2.getLocation().getAddr()).isEqualTo(team2.getLocation().getAddr());
    assertThat(result2.getProjects()).hasSize(3);
  }

}
