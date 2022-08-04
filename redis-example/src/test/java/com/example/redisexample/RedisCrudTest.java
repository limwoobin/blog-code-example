package com.example.redisexample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("zz")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisCrudTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    void setUp() {
        redisTemplate.opsForValue().set("a", "123");
    }

    @AfterEach
    void teardown() {
        redisTemplate.delete("a");
    }

    @Test
    void redisTest() {
        redisTemplate.opsForValue().set("a", "123");

        String value = (String) redisTemplate.opsForValue().get("a");

        System.out.println("value: " + value);
        assertThat(value).isEqualTo("123");
    }
}
