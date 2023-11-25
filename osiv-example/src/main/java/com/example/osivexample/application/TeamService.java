package com.example.osivexample.application;

import com.example.osivexample.domain.Team;
import com.example.osivexample.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {
  private final TeamRepository teamRepository;

  @Transactional(readOnly = true)
  public Team findTeam(Long id) {
    return teamRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Not Found Team"));
  }
}
