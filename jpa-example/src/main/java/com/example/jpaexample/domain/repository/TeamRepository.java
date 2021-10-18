package com.example.jpaexample.domain.repository;

import com.example.jpaexample.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team , Long> {
}
