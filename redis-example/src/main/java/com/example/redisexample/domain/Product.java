package com.example.redisexample.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("product")
@Builder
public class Product {
    @Id
    private String id;

    private String name;

    private Long price;

    public void changePrice(Long price) {
        this.price = price;
    }
}
