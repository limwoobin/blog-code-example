package com.example.springasync;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class SpringAsyncApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAsyncApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext context) {
    return args -> {
      AsyncTaskExecutor executor = context.getBean(AsyncTaskExecutor.class);
      ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) executor;
      System.out.println("AsyncTaskExecutor class: " + executor.getClass().getName());
      System.out.println("core size: " + taskExecutor.getCorePoolSize());
    };
  }

}
