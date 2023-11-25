package com.example.osivexample.presentation;

import com.example.osivexample.domain.Team;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TeamResponse {
  private Long id;
  private String name;
  private List<MemberResponse> members;

  public TeamResponse(Team team) {
    this.id = team.getId();
    this.name = team.getName();
    this.members = team.getMembers().stream()
      .map(MemberResponse::new)
      .collect(Collectors.toList());
  }
}
