package com.example.osivexample.intfrastructure;

import com.example.osivexample.application.TeamService;
import com.example.osivexample.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

  private final TeamService teamService;

  @KafkaListener(
    topics = "${spring.kafka.topics.exam}",
    groupId = "${spring.kafka.consumer.group-id}",
    containerFactory = "containerFactory"
  )
  public void consume(@Payload Long id, Acknowledgment ack) {
    Team team = teamService.findTeam(id);

    System.out.println(team.getMembers());
    ack.acknowledge();
  }
}
