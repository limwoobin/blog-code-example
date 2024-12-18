package org.example.ormbulkinsert.application;

import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

  private final TeamRepository teamRepository;
  private final TeamBatchRepository teamBatchRepository;

  public TeamService(TeamRepository teamRepository,
                     TeamBatchRepository teamBatchRepository) {
    this.teamRepository = teamRepository;
    this.teamBatchRepository = teamBatchRepository;
  }

  @Transactional
  public void save(Team team) {
    teamRepository.save(team);
  }

//  @Transactional
  public void saveAll(List<Team> teams) {
    teamBatchRepository.saveAll(teams);
  }
}
