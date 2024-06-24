package com.example.springeventexample.user.domain;

import com.example.springeventexample.mail.MailSenderEvent;
import com.example.springeventexample.mail.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class UserEventListener {
  private final MailSenderService mailSenderService;

  public UserEventListener(MailSenderService mailSenderService) {
    this.mailSenderService = mailSenderService;
  }

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void listen(MailSenderEvent event) {
    try {
      Thread.sleep(500);
      log.info("event listen start ...");
      mailSenderService.send(event.getEmail());
      log.info("event listen end ...");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
