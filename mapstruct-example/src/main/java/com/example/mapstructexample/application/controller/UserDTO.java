package com.example.mapstructexample.application.controller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String name;

    private int age;

    private String address;
}
