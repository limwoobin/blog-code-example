package com.example.springasync.controller;

import com.example.springasync.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class ExampleController {
  private final AsyncService asyncService;

  public ExampleController(AsyncService asyncService) {
    this.asyncService = asyncService;
  }

  @GetMapping
  public ResponseEntity<Void> temp() {
    log.info("controller start ...");
    asyncService.asyncMethod();
    log.info("controller end ...");
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
