package org.example.ormbulkinsert.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "project_code")
  private String projectCode;

  @Column(name = "level")
  private int level;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ProjectStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  @Builder
  public Project(String name,
                 String projectCode,
                 int level,
                 String description,
                 ProjectStatus status,
                 Team team) {
    this.name = name;
    this.projectCode = projectCode;
    this.level = level;
    this.description = description;
    this.status = status;
    this.team = team;
  }

  public void addTeam(Team team) {
    this.team = team;
  }
}

