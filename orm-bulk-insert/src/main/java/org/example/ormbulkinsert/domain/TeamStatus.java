package org.example.ormbulkinsert.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeamStatus {
  ACTIVE("활성"),
  DISABLED("비활성");

  private final String description;
}
