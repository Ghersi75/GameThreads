package com.gamethreads.gamethreads.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamethreads.gamethreads.dto.user.NewUserDTO;
import com.gamethreads.gamethreads.model.User;
import com.gamethreads.gamethreads.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }

  public User createUser(NewUserDTO newUserDTO) {
    String hashedPassword = encoder.encode(newUserDTO.getPassword());
    newUserDTO.setPassword(hashedPassword);

    User newUser = new User(newUserDTO);
    return this.userRepository.save(newUser);
  }
}
