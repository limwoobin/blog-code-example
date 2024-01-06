package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.TeamHistory;
import com.example.transactionpropagation.domain.repository.TeamHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
