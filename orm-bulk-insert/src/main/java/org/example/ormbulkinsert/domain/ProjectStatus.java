package org.example.ormbulkinsert.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectStatus {

  NOT_STARTED("미진행"),
  IN_PROGRESS("진행중"),
  PENDING("보류"),
  DONE("완료");


  private final String description;
}
