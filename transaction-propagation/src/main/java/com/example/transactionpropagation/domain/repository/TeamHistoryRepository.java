package com.example.transactionpropagation.domain.repository;

import com.example.transactionpropagation.domain.TeamHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamHistoryRepository extends JpaRepository<TeamHistory, Long> {
}
