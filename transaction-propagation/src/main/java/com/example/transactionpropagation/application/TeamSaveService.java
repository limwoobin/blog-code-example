package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@RequiredArgsConstructor
public class TeamSaveService {
  private final TeamRepository teamRepository;

//  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Transactional
  public void save(String name) {
    boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
    String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
    System.out.println("transactionActive: " + transactionActive);
    System.out.println("transactionName: " + transactionName);

    throw new RuntimeException();
  }
}
