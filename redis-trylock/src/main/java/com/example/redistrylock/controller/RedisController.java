package com.example.redistrylock.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

  private final RedissonClient redissonClient;

  public RedisController(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
  }

  @GetMapping(value = "/{key}")
  public String getValue(@PathVariable String key) {
    SerializationCodec codec = new SerializationCodec();
    RBucket<String> result = redissonClient.getBucket(key, codec);
    String existValue = result.get();
    System.out.println("existValue: " + existValue);

    String redisValue = key + "_REDIS";
    result.setIfAbsent(redisValue);

    return key;
  }
}
