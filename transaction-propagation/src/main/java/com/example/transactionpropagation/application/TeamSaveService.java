package com.example.transactionpropagation.application;

import com.example.transactionpropagation.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TeamSaveService {
  private final TeamRepository teamRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void save(String name) {
    throw new RuntimeException();
  }
}
