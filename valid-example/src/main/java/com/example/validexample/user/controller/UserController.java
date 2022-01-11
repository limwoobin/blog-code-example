package com.example.validexample.user.controller;

import com.example.validexample.user.domain.UserDTO;
import com.example.validexample.user.domain.UserRequest;
import com.example.validexample.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "")
    public ResponseEntity create(@Valid @RequestBody UserRequest userRequest , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors() , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
