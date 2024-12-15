package org.example.ormbulkinsert.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Getter
@Setter
public class TeamBatchDto {

  @Transient
  private Long id;
  private String name;
  private TeamStatus status;
  private String country;
  private String city;
  private String addr;
  private int zip_code;
  private String etc;

  @Transient
  private List<ProjectBatchDto> projects;

  @Builder
  public TeamBatchDto(String name,
                      TeamStatus status,
                      String country,
                      String city,
                      String addr,
                      int zip_code,
                      String etc,
                      List<ProjectBatchDto> projects) {
    this.name = name;
    this.status = status;
    this.country = country;
    this.city = city;
    this.addr = addr;
    this.zip_code = zip_code;
    this.etc = etc;
    this.projects = projects;
  }

  public static TeamBatchDto from(Team team) {
    Location location = team.getLocation();
    List<ProjectBatchDto> projects = team.getProjects().stream()
      .map(ProjectBatchDto::from)
      .toList();

    return TeamBatchDto.builder()
      .name(team.getName())
      .status(team.getStatus())
      .country(location.getCountry())
      .city(location.getCity())
      .addr(location.getAddr())
      .zip_code(location.getZipCode())
      .etc(location.getEtc())
      .projects(projects)
      .build();
  }

  public List<ProjectBatchDto> getProjectsWithFk() {
    this.projects.forEach(it -> it.setTeamId(this.id));
    return this.projects;
  }
}
