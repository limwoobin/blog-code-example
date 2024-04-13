package com.example.kafkaitemreaderexample;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class KafkaItemReaderExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaItemReaderExampleApplication.class, args);
  }

}
