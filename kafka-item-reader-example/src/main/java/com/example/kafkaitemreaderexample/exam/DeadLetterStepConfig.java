package com.example.kafkaitemreaderexample.exam;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DeadLetterStepConfig {
  public static final String STEP_NAME = "deadLetterStep";

  private final DeadLetterItemReader deadLetterItemReader;
  private final DeadLetterItemWriter deadLetterItemWriter;

  public DeadLetterStepConfig(DeadLetterItemReader deadLetterItemReader,
                              DeadLetterItemWriter deadLetterItemWriter) {
    this.deadLetterItemReader = deadLetterItemReader;
    this.deadLetterItemWriter = deadLetterItemWriter;
  }

  @Bean
  @JobScope
  public Step deadLetterStep(final JobRepository jobRepository,
                             final PlatformTransactionManager platformTransactionManager) {
    return new StepBuilder(STEP_NAME, jobRepository)
      .<String, String>chunk(10, platformTransactionManager)
      .reader(deadLetterItemReader.deadLetterKafkaItemReader())
      .writer(deadLetterItemWriter)
      .build();
  }

}
