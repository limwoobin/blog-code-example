package org.example.ormbulkinsert.repository;

import org.example.ormbulkinsert.domain.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeamJdbcRepository {
  private final JdbcTemplate jdbcTemplate;

  public TeamJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Transactional
  public void save(List<Team> teams) {
//    jdbcTemplate.batchUpdate()
  }
}
