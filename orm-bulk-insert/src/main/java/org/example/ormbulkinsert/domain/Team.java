package org.example.ormbulkinsert.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private TeamStatus status;

  @Embedded
  private Location location;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
  private List<Project> projects = new ArrayList<>();

  @Builder
  public Team(String name,
              TeamStatus status,
              Location location) {
    this.name = name;
    this.status = status;
    this.location = location;
  }

  public void addLocation(Location location) {
    this.location = location;
  }

  public void addProject(Project project) {
    this.projects.add(project);
    project.addTeam(this);
  }

  public void addProjects(List<Project> projects) {
    this.projects.addAll(projects);
    projects.forEach(project -> project.addTeam(this));
  }

  public void setId(Long id) {
    this.id = id;
  }
}
