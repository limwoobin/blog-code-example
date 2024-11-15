package org.example.ormbulkinsert;

import org.example.ormbulkinsert.domain.*;
import org.example.ormbulkinsert.genrator.BulkInsertFactory;
import org.example.ormbulkinsert.genrator.TempGeneratorV3;
import org.junit.jupiter.api.Test;

import java.util.List;

class GeneratorTest {

  @Test
  void test() throws IllegalAccessException {
    Location location = Location.builder()
      .country("korea")
      .city("seoul")
      .addr("gangnam")
      .zipCode(12345)
      .build();

    Team team = Team.builder()
      .name("team")
      .status(TeamStatus.ACTIVE)
      .location(location)
      .build();

    Location location2 = Location.builder()
      .country("korea")
      .city("seoul")
      .addr("guro")
      .zipCode(54321)
      .build();

    Team team2 = Team.builder()
      .name("team-2")
      .status(TeamStatus.ACTIVE)
      .location(location2)
      .build();

    List<Team> teams = List.of(team, team2);
    BulkInsertFactory bulkInsertFactory = new BulkInsertFactory();
    String result = bulkInsertFactory.bulkInsertQueryV2(teams);
    System.out.println(result);
  }

  @Test
  void test3() throws IllegalAccessException {
    Location location = Location.builder()
      .country("korea")
      .city("seoul")
      .addr("gangnam")
      .zipCode(12345)
      .build();

    Team team = Team.builder()
      .name("team")
      .status(TeamStatus.ACTIVE)
      .location(location)
      .build();

    Location location2 = Location.builder()
      .country("korea")
      .city("seoul")
      .addr("guro")
      .zipCode(54321)
      .build();

    Team team2 = Team.builder()
      .name("team-2")
      .status(TeamStatus.ACTIVE)
      .location(location2)
      .build();

    List<Team> teams = List.of(team, team2);
    String result = TempGeneratorV3.generateBulkInsertStatement(teams);
    System.out.println(result);
  }


}
