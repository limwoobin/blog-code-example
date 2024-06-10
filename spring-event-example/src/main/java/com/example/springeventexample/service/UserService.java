package com.example.springeventexample.service;

import com.example.springeventexample.domain.User;
import com.example.springeventexample.domain.UserEvent;
import com.example.springeventexample.domain.UserRepository;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public void save(String name) {
    User user = User.from(name);
    userRepository.save(user);

    eventPublisher.publishEvent(new UserEvent(name));
    System.out.println("service end");
  }
}
