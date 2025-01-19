package com.gamethreads.gamethreads.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamethreads.gamethreads.model.User;
import com.gamethreads.gamethreads.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }
}
