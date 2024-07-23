package com.example.redistrylock;

import com.example.redistrylock.service.LockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LockTest {

  @Autowired
  private LockService lockService;

  @Test
  void test() throws InterruptedException {
    // given
    int threadSize = 500;
    ExecutorService executor = Executors.newFixedThreadPool(threadSize);

    // when
    CountDownLatch latch = new CountDownLatch(threadSize);

    for (int i = 0; i < threadSize; i++) {
      executor.execute(() -> {
        lockService.increment();
        latch.countDown();
      });
    }

    latch.await();

    // then
    executor.shutdown();
    int result = lockService.getCount();

    assertThat(result).isEqualTo(threadSize);
  }

  @Test
  void test2() {
    // given
    int threadSize = 100;
    Runnable runnable = () -> lockService.incrementV2();
    List<CompletableFuture<Void>> completableFutures = new ArrayList<>(threadSize);

    // when
    for (int i = 0; i < threadSize; i++) {
      CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable).toCompletableFuture();
      completableFutures.add(completableFuture);
    }

    CompletableFuture<Void> allOf = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
    allOf.join();

    // then
    int result = lockService.getCount();
    assertThat(result).isEqualTo(threadSize);
  }

  @Test
  public void singleTest() throws InterruptedException {
    Thread thread = new Thread(() -> lockService.incrementOtherThread());
    Thread thread2 = new Thread(() -> lockService.incrementOtherThread());
    Thread thread3 = new Thread(() -> lockService.incrementOtherThread());
    Thread thread4 = new Thread(() -> lockService.incrementOtherThread());

    thread.start();

    Thread.sleep(100);
    thread2.start();
    thread3.start();
    thread4.start();

    Thread.sleep(100);
    lockService.increment();
    lockService.incrementOtherThread();
  }
}
