package com.example.springconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ExamComponent {

  @Bean(name = "exam2")
  public Exam exam() {
    return new Exam();
  }
}
