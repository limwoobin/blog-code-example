package org.example.ormbulkinsert.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
public class ProjectBatchDto {
  @Transient
  private Long id;
  private String name;
  private String project_code;
  private int level;
  private String description;
  private ProjectStatus status;
  private Long team_id;

  @Builder
  public ProjectBatchDto(String name,
                         String projectCode,
                         int level,
                         String description,
                         ProjectStatus status,
                         Long teamId) {
    this.name = name;
    this.project_code = projectCode;
    this.level = level;
    this.description = description;
    this.status = status;
    this.team_id = teamId;
  }

  public static ProjectBatchDto from(Project project) {
    return ProjectBatchDto.builder()
      .name(project.getName())
      .projectCode(project.getProjectCode())
      .level(project.getLevel())
      .description(project.getDescription())
      .status(project.getStatus())
      .build();
  }
}
