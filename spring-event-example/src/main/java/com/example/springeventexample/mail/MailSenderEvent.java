package com.example.springeventexample.mail;

import lombok.Getter;

@Getter
public class MailSenderEvent {

  private String email;

  public MailSenderEvent(String email) {
    this.email = email;
  }
}
