package com.example.jpawhereannotation.domain.value;

import lombok.Getter;

@Getter
public class MemberDto {

  private Long memberId;
  private String name;
  private Long teamId;

  public MemberDto(Long memberId, String name, Long teamId) {
    this.memberId = memberId;
    this.name = name;
    this.teamId = teamId;
  }
}
