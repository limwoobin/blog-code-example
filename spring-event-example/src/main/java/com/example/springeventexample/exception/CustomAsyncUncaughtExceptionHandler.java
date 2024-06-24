//package com.example.springeventexample.exception;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//
//import java.lang.reflect.Method;
//
//public class CustomAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
//
//  @Override
//  public void handleUncaughtException(Throwable ex, Method method, Object... params) {
//    System.out.println("Exception message - " + ex.getMessage());
//    System.out.println("Method name - " + method.getName());
//
//    for (Object param : params) {
//      System.out.println("Parameter value - " + param);
//    }
//  }
//
//}
