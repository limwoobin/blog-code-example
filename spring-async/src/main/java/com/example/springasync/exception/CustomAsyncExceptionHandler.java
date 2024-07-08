package com.example.springasync.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
  @Override
  public void handleUncaughtException(Throwable ex, Method method, Object... params) {
    log.error("error message: {}", ex.getMessage());
  }
}
