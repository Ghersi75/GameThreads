package com.gamethreads.gamethreads.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamethreads.gamethreads.dto.user.NewUserDTO;
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

  public User createUser(NewUserDTO newUserDTO) {
    User newUser = new User(newUserDTO);

    return this.userRepository.save(newUser);
  }
}
