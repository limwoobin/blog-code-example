package com.example.kafkaitemreaderexample.exam;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterJobConfig {
  public static final String JOB_NAME = "deadLetterJob";

  private final Step deadLetterStep;

  public DeadLetterJobConfig(Step deadLetterStep) {
    this.deadLetterStep = deadLetterStep;
  }

  @Bean
  public Job deadLetterJob(final JobRepository jobRepository) {
    return new JobBuilder(JOB_NAME, jobRepository)
      .start(deadLetterStep)
      .build();
  }
}
