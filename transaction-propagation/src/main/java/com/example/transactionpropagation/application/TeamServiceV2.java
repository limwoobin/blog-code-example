package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamServiceV2 {
  private final TeamSaveService teamSaveService;
  private final TeamRepository teamRepository;

//  @Transactional(rollbackFor = Throwable.class)
  @Transactional
  public void save(String name) {
    try {
      Team team = Team.from(name);
      teamRepository.save(team);
      teamSaveService.save(name);
    } catch (Exception e) {
      e.printStackTrace();
//      throw e;
    }
  }
}
