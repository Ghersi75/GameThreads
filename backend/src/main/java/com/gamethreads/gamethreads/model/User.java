package com.gamethreads.gamethreads.model;

import com.gamethreads.gamethreads.dto.user.NewUserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String displayName;
  private String email;
  private String password;

  public User(NewUserDTO newUserDTO) {
    this.displayName = newUserDTO.getDisplayName();
    this.email = newUserDTO.getEmail();
    this.password = newUserDTO.getPassword();
  }
}
