package com.example.jpawhereannotation.domain.repository;

import com.example.jpawhereannotation.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
