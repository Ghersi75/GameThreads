package com.gamethreads.gamethreads.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamethreads.gamethreads.dto.user.NewUserDTO;
import com.gamethreads.gamethreads.model.User;
import com.gamethreads.gamethreads.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public List<User> getAllUsersHandler() {
    return this.userService.getAllUsers();
  }

  @PostMapping("/new")
  public User createUserHandler(@RequestBody @Valid NewUserDTO newUserDTO) {
    return this.userService.createUser(newUserDTO);
  }
}
