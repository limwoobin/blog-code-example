package com.example.kafkaitemreaderexample.exam;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterItemWriter implements ItemWriter<String> {

  @Override
  public void write(Chunk<? extends String> items) {
    for (String item : items) {
      System.out.println(item);
    }

    System.out.println("-----------------------------------");
  }
}
