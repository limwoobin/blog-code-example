package org.example.ormbulkinsert.fixture;

import org.example.ormbulkinsert.domain.Project;
import org.example.ormbulkinsert.domain.ProjectStatus;

public class ProjectFixture {
  public static Project PROJECT_1() {
    return Project.builder()
      .name("project")
      .projectCode("code-1001")
      .level(3)
      .status(ProjectStatus.NOT_STARTED)
      .build();
  }

  public static Project PROJECT_2() {
    return Project.builder()
      .name("project2")
      .projectCode("code-1002")
      .level(2)
      .status(ProjectStatus.IN_PROGRESS)
      .build();
  }

  public static Project PROJECT_3() {
    return Project.builder()
      .name("project3")
      .projectCode("code-2001")
      .level(3)
      .status(ProjectStatus.IN_PROGRESS)
      .build();
  }

  public static Project PROJECT_4() {
    return Project.builder()
      .name("project4")
      .projectCode("code-2002")
      .level(4)
      .status(ProjectStatus.PENDING)
      .build();
  }

  public static Project PROJECT_5() {
    return Project.builder()
      .name("project5")
      .projectCode("code-2003")
      .level(5)
      .status(ProjectStatus.DONE)
      .build();
  }

  public static Project PROJECT_6() {
    return Project.builder()
      .name("project6")
      .projectCode("code-2004")
      .level(5)
      .status(ProjectStatus.DONE)
      .build();
  }
}
