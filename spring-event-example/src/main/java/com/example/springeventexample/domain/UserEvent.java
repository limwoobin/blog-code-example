package com.example.springeventexample.domain;

import lombok.Getter;

@Getter
public class UserEvent {

  private String name;

  public UserEvent(String name) {
    this.name = name;
  }
}
