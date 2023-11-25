package com.example.osivexample.presentation;

import com.example.osivexample.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
  private Long id;
  private String name;

  public MemberResponse(Member member) {
    this.id = member.getId();
    this.name = member.getName();
  }
}
