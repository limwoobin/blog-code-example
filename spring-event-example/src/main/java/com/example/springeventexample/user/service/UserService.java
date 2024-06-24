package com.example.springeventexample.user.service;

import com.example.springeventexample.user.domain.User;
import com.example.springeventexample.user.domain.UserDto;
import com.example.springeventexample.mail.MailSenderEvent;
import com.example.springeventexample.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;
  private final ApplicationEventPublisher eventPublisher;

  public UserService(UserRepository userRepository,
                     ApplicationEventPublisher eventPublisher) {
    this.userRepository = userRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  public void save(UserDto userDto) {
    User user = User.of(userDto.getName(), userDto.getEmail());
    userRepository.save(user);

    log.info("event publish start ...");
    eventPublisher.publishEvent(new MailSenderEvent(user.getEmail()));
    log.info("event publish end ...");
  }
}
