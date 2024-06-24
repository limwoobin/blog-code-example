package com.example.springeventexample.controller;

import com.example.springeventexample.user.domain.UserDto;
import com.example.springeventexample.user.service.UserService;
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

  @PostMapping
  public ResponseEntity<String> save(@RequestBody UserDto userDto) {
    userService.save(userDto);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
