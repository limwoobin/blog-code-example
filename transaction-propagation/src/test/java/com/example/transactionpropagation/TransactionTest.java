package com.example.transactionpropagation;


import com.example.transactionpropagation.application.TeamService;
import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.TeamHistory;
import com.example.transactionpropagation.domain.repository.TeamHistoryRepository;
import com.example.transactionpropagation.domain.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionTest {

  @Autowired
  private TeamService teamService;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private TeamHistoryRepository teamHistoryRepository;

  @DisplayName(value = "자식 트랜잭션에서 예외가 없으면 부모,자식 트랜잭션 모두 커밋된다")
  @Test
  void test() {
    // given
    String teamName = "test-team";

    // when
    teamService.save(teamName);

    // then
    Optional<Team> team = teamRepository.findByName(teamName);
    assertThat(team.isPresent()).isTrue();

    List<TeamHistory> histories = teamHistoryRepository.findByTeam(team.get());
    assertThat(histories.isEmpty()).isFalse();
  }
}
