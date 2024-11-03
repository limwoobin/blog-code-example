package com.example.springtestexample.service;

import org.springframework.stereotype.Service;

@Service
public class TempService {

  private final MyService customMyService;

  public TempService(MyService customMyService) {
    this.customMyService = customMyService;
  }

  public String run() {
    return customMyService.get();
  }
}
