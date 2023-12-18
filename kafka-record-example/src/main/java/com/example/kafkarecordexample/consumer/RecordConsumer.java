package com.example.kafkarecordexample.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RecordConsumer {

  @KafkaListener(
    topics = "${spring.kafka.topics.record-test}",
    groupId = "${spring.kafka.consumer.group-id}",
    containerFactory = "containerFactory"
  )
  public void consume(@Payload String message) {
    log.info("message: {}", message);
  }
}
