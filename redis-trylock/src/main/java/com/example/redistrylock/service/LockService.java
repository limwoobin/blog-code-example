package com.example.redistrylock.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LockService {
  private final RedissonClient redissonClient;

  private int count;
  private static final String LOCK_NAME = "test:lock";

  public LockService(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
    this.count = 0;
  }

  public void increment() {
    System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getId());
    RLock lock = redissonClient.getLock(LOCK_NAME);

    try {
      lock.tryLock(300, 150, TimeUnit.SECONDS);
      Thread.sleep(200);
      count++;
      System.out.println("count: " + count);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }

  public void incrementOtherThread() {
    System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getId());
    RLock lock = redissonClient.getLock(LOCK_NAME);

    try {
      lock.tryLock(300, 295, TimeUnit.SECONDS);
      Thread.sleep(290000);
      count++;
      System.out.println("count: " + count);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }

  public void incrementV2() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    count++;
    System.out.println("count: " + count);
  }

  public int getCount() {
    return count;
  }
}
