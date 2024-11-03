package com.example.springtestexample.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

  private final MyComponent myComponent;

  public MyService(MyComponent myComponent) {
    this.myComponent = myComponent;
  }

  public String get() {
    return "MyService";
  }

  public String getComponentValue(String param) {
    return myComponent.get() + param;
  }

}
