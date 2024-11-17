package org.example.ormbulkinsert.repository;

import org.example.ormbulkinsert.domain.Project;
import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.genrator.BulkInsertQueryFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class TeamJdbcRepository {
  private final JdbcTemplate jdbcTemplate;

  private static final String LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

  public TeamJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Transactional
  public void save(List<Team> teams) throws IllegalAccessException {
    String sql = BulkInsertQueryFactory.bulkInsertQuery(teams);
    jdbcTemplate.batchUpdate(sql);

    Long firstFk = jdbcTemplate.queryForObject(LAST_INSERT_ID, Long.class);
    if (firstFk == null) {
      throw new IllegalStateException("Failed to get last insert id");
    }

    for (int i = 0; i < teams.size(); i++) {
      Team team = teams.get(i);
      team.setId(firstFk + i);
    }

    List<Project> members = teams.stream()
      .map(Team::getProjects)
      .flatMap(Collection::stream)
      .toList();

    String sql2 = BulkInsertQueryFactory.bulkInsertQuery(members);
    jdbcTemplate.batchUpdate(sql2);
  }
}
