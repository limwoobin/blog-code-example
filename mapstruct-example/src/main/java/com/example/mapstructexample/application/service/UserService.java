package com.example.mapstructexample.application.service;

import com.example.mapstructexample.application.controller.UserDTO;
import com.example.mapstructexample.application.domain.User;
import com.example.mapstructexample.application.mapper.UserMapper;
import com.example.mapstructexample.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper = UserMapper.INSTANCE;

    public UserDTO create(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return userMapper.toUserDTO(userRepository.save(user));
    }
}
