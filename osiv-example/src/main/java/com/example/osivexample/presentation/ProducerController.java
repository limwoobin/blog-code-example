package com.example.osivexample.presentation;

import com.example.osivexample.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

  private final KafkaTemplate<String, Long> kafkaTemplate;

  @Value("${spring.kafka.topics.osiv-test}")
  private String topic;

  @GetMapping(value = "/produce/{id}")
  public ResponseEntity<Void> produce(@PathVariable Long id) {
    kafkaTemplate.send(topic, id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
