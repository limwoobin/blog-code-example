package org.example.ormbulkinsert.bulk_Insert;

import org.example.ormbulkinsert.domain.*;
import org.example.ormbulkinsert.fixture.LocationFixture;
import org.example.ormbulkinsert.fixture.ProjectFixture;
import org.example.ormbulkinsert.fixture.TeamFixture;
import org.example.ormbulkinsert.genrator.BulkInsertQueryFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

class GeneratorTest {

  @Test
  void test() throws IllegalAccessException {
    Team team = TeamFixture.TEAM_1();
    Location seoul = LocationFixture.LOCATION_GANGNAM();
    team.addLocation(seoul);

    Team team2 = TeamFixture.TEAM_2();
    Location guro = LocationFixture.LOCATION_GURO();
    team2.addLocation(guro);

    List<Team> teams = List.of(team, team2);
    BulkInsertQueryFactory bulkInsertQueryFactory = new BulkInsertQueryFactory();
    String result = bulkInsertQueryFactory.bulkInsertQuery(teams);
    System.out.println(result);
  }

  @Test
  void test2() throws IllegalAccessException {
    // given
    Team team = TeamFixture.TEAM_1();
    Location seoul = LocationFixture.LOCATION_GANGNAM();
    team.addLocation(seoul);
    team.setId(1L);

    Project project = ProjectFixture.PROJECT_1();
    project.addTeam(team);

    // then
    List<Project> projects = List.of(project);
    BulkInsertQueryFactory bulkInsertQueryFactory = new BulkInsertQueryFactory();
    String result = bulkInsertQueryFactory.bulkInsertQuery(projects);
    System.out.println(result);
  }
}
