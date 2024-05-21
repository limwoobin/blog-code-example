package com.example.springconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExamConfiguration {

  @Bean
  public Exam exam() {
    return new Exam();
  }

}
