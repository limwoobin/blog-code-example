package com.example.osivexample.intfrastructure;

import com.example.osivexample.application.TeamService;
import com.example.osivexample.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

  private final TeamService teamService;

  @KafkaListener(
    topics = "${spring.kafka.topics.osiv-test}",
    groupId = "${spring.kafka.consumer.group-id}",
    containerFactory = "containerFactory"
  )
  public void consume(@Payload Long id, Acknowledgment ack) {
    Team team = teamService.findTeam(id);

    log.info("member size = {}", team.getMembers().size());
    ack.acknowledge();
  }
}
