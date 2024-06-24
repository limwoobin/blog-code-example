package com.example.springeventexample.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class MailSenderService {

  @Transactional
  public void send(String email) {
    log.info("{} Mail Send", email);
  }
}
