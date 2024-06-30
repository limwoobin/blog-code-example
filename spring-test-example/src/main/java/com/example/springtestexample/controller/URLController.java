package com.example.springtestexample.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class URLController {

  @GetMapping(value = "/short")
  public ResponseEntity<Void> get() {
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create("https://devoong2.tistory.com"));
    return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
  }

}
