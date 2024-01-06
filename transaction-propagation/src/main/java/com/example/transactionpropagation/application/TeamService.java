package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
  private final TeamRepository teamRepository;
  private final TeamHistoryService teamHistoryService;

  @Transactional
  public void save(String name) {
    Optional<Team> optionalTeam = teamRepository.findByName(name);
    if (optionalTeam.isPresent()) {
      throw new RuntimeException();
    }

    Team team = Team.from(name);
    teamRepository.save(team);
    teamHistoryService.saveHistory(team);
  }

  @Transactional
  public void save2(String name) {
    Optional<Team> optionalTeam = teamRepository.findByName(name);
    if (optionalTeam.isPresent()) {
      throw new RuntimeException();
    }

    Team team = Team.from(name);
    teamRepository.save(team);
    teamHistoryService.saveHistory2(team);
  }

  @Transactional
  public void save3(String name) {
    Optional<Team> optionalTeam = teamRepository.findByName(name);
    if (optionalTeam.isPresent()) {
      throw new RuntimeException();
    }

    Team team = Team.from(name);
    teamRepository.save(team);
    teamHistoryService.saveHistory(team);

    throw new RuntimeException();
  }
}
