package com.example.kafkaitemreaderexample.exam;

import org.apache.kafka.common.TopicPartition;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class DeadLetterItemReader {

  private final KafkaProperties kafkaProperties;
  private final SslBundles sslBundles;

  public DeadLetterItemReader(KafkaProperties kafkaProperties, SslBundles sslBundles) {
    this.kafkaProperties = kafkaProperties;
    this.sslBundles = sslBundles;
  }

  @Value("${spring.kafka.topics.dead-letter}")
  private String topic;


  public KafkaItemReader<String, String> deadLetterKafkaItemReader() {
    Properties props = new Properties();
    props.putAll(kafkaProperties.buildConsumerProperties(sslBundles));
    props.put("group.id", "dlt-consumer-group");

    return new KafkaItemReaderBuilder<String, String>()
      .name("deadLetterKafkaItemReader")
      .topic(topic)
      .partitions(0)
      .consumerProperties(props)
      .pollTimeout(Duration.ofSeconds(5L))
      .partitionOffsets(new HashMap<>())
      .saveState(false)
      .build();
  }

}
