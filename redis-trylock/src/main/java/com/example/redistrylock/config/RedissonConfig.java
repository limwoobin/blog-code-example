package com.example.redistrylock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

  private static final String REDISSON_HOST_PREFIX = "redis://";

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress(REDISSON_HOST_PREFIX + "127.0.0.1:6379");
    return Redisson.create(config);
  }

//  @Bean
//  public RedissonClient redissonClient(RedissonAutoConfiguration redissonAutoConfiguration) throws IOException {
//    return redissonAutoConfiguration.redisson();
//  }
}
