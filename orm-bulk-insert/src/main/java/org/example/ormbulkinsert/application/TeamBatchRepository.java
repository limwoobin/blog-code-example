package org.example.ormbulkinsert.application;

import org.example.ormbulkinsert.domain.ProjectBatchDto;
import org.example.ormbulkinsert.domain.Team;
import org.example.ormbulkinsert.domain.TeamBatchDto;
import org.springframework.data.annotation.Transient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeamBatchRepository {
  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private static final String LAST_INSERT_ID_SQL = "SELECT LAST_INSERT_ID()";

  public TeamBatchRepository(JdbcTemplate jdbcTemplate,
                             NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Transactional
  public void saveAll(List<Team> teams) {
    List<TeamBatchDto> teamBatchDtos = teams.stream()
      .map(TeamBatchDto::from)
      .toList();

    String sql = generateSql(TableConstants.TEAM_TABLE, TeamBatchDto.class);
    SqlParameterSource[] batchParams = teamBatchDtos.stream()
      .map(CustomBeanPropertySqlParameterSource::new)
      .toArray(SqlParameterSource[]::new);

    namedParameterJdbcTemplate.batchUpdate(sql, batchParams);

    Long lastInsertId = jdbcTemplate.queryForObject(LAST_INSERT_ID_SQL, Long.class);
    saveProjects(teamBatchDtos, lastInsertId);
  }

  private void saveProjects(List<TeamBatchDto> teams, Long lastInsertId) {
    for (int i = 0; i < teams.size(); i++) {
      TeamBatchDto team = teams.get(i);
      team.setId(lastInsertId + i);
    }

    List<ProjectBatchDto> projects = teams.stream()
      .map(TeamBatchDto::getProjectsWithFk)
      .flatMap(List::stream)
      .toList();

    String sql = generateSql(TableConstants.PROJECT_TABLE, ProjectBatchDto.class);
    SqlParameterSource[] batchParams = projects.stream()
      .map(CustomBeanPropertySqlParameterSource::new)
      .toArray(SqlParameterSource[]::new);

    namedParameterJdbcTemplate.batchUpdate(sql, batchParams);
  }

  private String generateSql(String tableName, Class<?> clazz) {
    List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
      .filter(it -> !it.isAnnotationPresent(Transient.class))
      .toList();

    String columnsStr = fields.stream()
      .map(Field::getName)
      .collect(Collectors.joining(", "));
    String parameterColumnsStr = fields.stream()
      .map(it -> ":" + it.getName())
      .collect(Collectors.joining(", "));

    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO ")
      .append(tableName)
      .append("(").append(columnsStr).append(") ")
      .append("VALUES ")
      .append("(").append(parameterColumnsStr).append(") ");

    return sb.toString();
  }
}
