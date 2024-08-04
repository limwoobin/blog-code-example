package com.example.tomcatthreadpool;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class ThreadPoolTest {

  private static final HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_1_1)
    .build();

  @Test
  void test() {
    final HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://127.0.0.1:8080"))
//      .timeout(Duration.ofSeconds(10))
      .build();

    Supplier<HttpResponse<String>> task = () -> {
      try {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };

    List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      CompletableFuture<HttpResponse<String>> future = CompletableFuture.supplyAsync(task);
      futures.add(future);
    }

    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    combinedFuture.join();

    for (CompletableFuture<HttpResponse<String>> future : futures) {
      try {
        HttpResponse<String> response = future.get();
        System.out.println("statusCode: " + response.statusCode());
        System.out.println("body: " + response.body());
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
