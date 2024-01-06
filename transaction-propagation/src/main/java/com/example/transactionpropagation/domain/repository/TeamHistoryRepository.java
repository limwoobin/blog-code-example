package com.example.transactionpropagation.domain.repository;

import com.example.transactionpropagation.domain.Team;
import com.example.transactionpropagation.domain.TeamHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamHistoryRepository extends JpaRepository<TeamHistory, Long> {
  List<TeamHistory> findByTeam(Team team);
}
