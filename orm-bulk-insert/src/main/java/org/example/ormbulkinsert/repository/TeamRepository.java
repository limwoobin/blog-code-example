package org.example.ormbulkinsert.repository;

import org.example.ormbulkinsert.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

  @Query("SELECT distinct t FROM Team t JOIN FETCH t.projects")
  List<Team> findAllWithProjects();
}
