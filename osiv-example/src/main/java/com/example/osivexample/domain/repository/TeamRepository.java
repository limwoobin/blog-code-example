package com.example.osivexample.domain.repository;

import com.example.osivexample.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
