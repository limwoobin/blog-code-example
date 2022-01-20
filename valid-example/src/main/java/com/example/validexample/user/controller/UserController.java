package com.example.validexample.user.controller;

import com.example.validexample.user.domain.UserDTO;
import com.example.validexample.user.domain.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping(value = "")
    public ResponseEntity create(@Valid UserRequest userRequest) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "")
    public ResponseEntity createForPost(@Valid @RequestBody UserRequest userRequest) {

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/v2")
    public ResponseEntity create(@Valid UserRequest userRequest , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            for(FieldError error : list) {
                return new ResponseEntity<>(error.getDefaultMessage() , HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/v2")
    public ResponseEntity createForPost(@Valid @RequestBody UserRequest userRequest , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            for(FieldError error : list) {
                return new ResponseEntity<>(error.getDefaultMessage() , HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
