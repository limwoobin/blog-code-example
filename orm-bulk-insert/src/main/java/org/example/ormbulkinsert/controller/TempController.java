package org.example.ormbulkinsert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

  @GetMapping
  public ResponseEntity<String> temp() {
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
