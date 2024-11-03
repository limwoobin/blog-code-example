package com.example.springtestexample.service;

import org.springframework.stereotype.Service;

@Service
public class TempService {

  private final MyService myService;

  public TempService(MyService myService) {
    this.myService = myService;
  }

  public String run() {
    return myService.get();
  }
}
