package com.example.redistrylock.controller;

import com.example.redistrylock.service.LockService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

  private final RedissonClient redissonClient;
  private final LockService lockService;

  public RedisController(RedissonClient redissonClient,
                         LockService lockService) {
    this.redissonClient = redissonClient;
    this.lockService = lockService;
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

  @GetMapping
  public String go() {
    lockService.incrementOtherThread();
    return "OK";
  }
}
