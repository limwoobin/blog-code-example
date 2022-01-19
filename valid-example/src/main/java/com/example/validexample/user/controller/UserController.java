package com.example.validexample.user.controller;

import com.example.validexample.user.domain.UserDTO;
import com.example.validexample.user.domain.UserRequest;
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

    @GetMapping(value = "")
    public ResponseEntity create(@Valid UserRequest userRequest) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "")
    public ResponseEntity createForPost(@Valid @RequestBody UserRequest userRequest) {

        return new ResponseEntity(HttpStatus.CREATED);
    }

//    @GetMapping(value = "")
//    public ResponseEntity create(@Valid UserRequest userRequest , BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors() , HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
