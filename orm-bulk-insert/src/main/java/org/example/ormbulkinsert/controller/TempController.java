package org.example.ormbulkinsert.controller;

import org.example.ormbulkinsert.service.TempService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

  private final TempService tempService;

  public TempController(TempService tempService) {
    this.tempService = tempService;
  }

  @GetMapping
  public ResponseEntity<String> temp() {
    tempService.print();
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
