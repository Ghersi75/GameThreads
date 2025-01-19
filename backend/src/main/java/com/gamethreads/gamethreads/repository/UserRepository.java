package com.gamethreads.gamethreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamethreads.gamethreads.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
