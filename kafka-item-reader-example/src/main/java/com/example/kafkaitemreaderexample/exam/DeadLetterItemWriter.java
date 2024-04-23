package com.example.kafkaitemreaderexample.exam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeadLetterItemWriter implements ItemWriter<String> {

  @Override
  public void write(Chunk<? extends String> items) {
    for (String item : items) {
      log.info("item: {}", item);
    }

    log.info("-----------------------------------");
  }
}
