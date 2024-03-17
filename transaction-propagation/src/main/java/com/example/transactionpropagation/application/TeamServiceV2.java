package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class TeamServiceV2 {
  private final TeamSaveService teamSaveService;
  private final TeamRepository teamRepository;

//  @Transactional(rollbackFor = Throwable.class)
  @Transactional
  public void save(String name) {
    boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
    String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
    System.out.println("transactionActive: " + transactionActive);
    System.out.println("transactionName: " + transactionName);

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
