package org.example.ormbulkinsert.application;

import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Transactional
  public void save(Team team) {
    teamRepository.save(team);
  }
}
