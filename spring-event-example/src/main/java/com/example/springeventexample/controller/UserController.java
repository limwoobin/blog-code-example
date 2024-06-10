package com.example.springeventexample.controller;

import com.example.springeventexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<String> find(@PathVariable Long id) {
    return new ResponseEntity<>("HI", HttpStatus.OK);
  }

  @PostMapping(value = "/{name}")
  public ResponseEntity<Void> save(@PathVariable String name) {
    userService.save(name);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
