package com.example.springeventexample.domain;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

  User findById(Long id);

  void save(User user);
}
