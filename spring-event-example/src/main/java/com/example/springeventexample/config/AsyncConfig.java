package com.example.springeventexample.config;

//import com.example.springeventexample.exception.CustomAsyncUncaughtExceptionHandler;
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

//  @Override
//  public Executor getAsyncExecutor() {
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setCorePoolSize(10);
//    executor.setMaxPoolSize(100);
//    executor.setQueueCapacity(50);
//    executor.initialize();
//    return executor;
//  }

//  @Override
//  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//    return new CustomAsyncUncaughtExceptionHandler();
//  }
}
