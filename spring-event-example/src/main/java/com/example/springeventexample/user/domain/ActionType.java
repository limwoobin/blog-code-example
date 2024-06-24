package com.example.springeventexample.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
  SIGN_UP("가입"),
  EDIT("수정"),
  DELETE("삭제");

  private final String description;
}
