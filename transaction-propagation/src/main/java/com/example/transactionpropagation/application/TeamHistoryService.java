package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.TeamHistory;
import com.example.transactionpropagation.domain.repository.TeamHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamHistoryService {
  private final TeamHistoryRepository teamHistoryRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveHistory(Team team) {
    TeamHistory teamHistory = TeamHistory.from(team);
    teamHistoryRepository.save(teamHistory);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveHistory2(Team team) {
    throw new RuntimeException();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveHistory3(Team team) {
    try {
      throw new RuntimeException();
    } catch (Exception e) {
      log.error("error message {}", e.getMessage());
    }
  }
}
