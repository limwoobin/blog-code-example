package org.example.ormbulkinsert.concurrency;

import org.example.ormbulkinsert.application.TeamBatchRepository;
import org.example.ormbulkinsert.domain.Location;
import org.example.ormbulkinsert.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class BulkTest {

  @Autowired
  private TeamBatchRepository teamBatchRepository;

  @Test
  void test() throws InterruptedException {
    Callable<String> task1 = () -> {
      teamBatchRepository.saveAll(generateTeams("team-1"));
      return "test-1";
    };

    Callable<String> task2 = () -> {
      teamBatchRepository.saveAll(generateTeams("team-2"));
      return "test-2";
    };

    Callable<String> task3 = () -> {
      teamBatchRepository.saveAll(generateTeams("team-3"));
      return "test-3";
    };

    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.invokeAll(List.of(task1, task2, task3));
    executorService.shutdown();
  }

  private List<Team> generateTeams(String name) {
    List<Team> teams = new ArrayList<>();
    Location location = Location.builder()
      .build();

    for (int i = 0; i < 50000; i++) {
      Team team = Team.builder()
        .name(name)
        .location(location)
        .build();

      teams.add(team);
    }

    return teams;
  }

}
