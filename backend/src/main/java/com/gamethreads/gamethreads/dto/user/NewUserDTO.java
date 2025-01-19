package com.gamethreads.gamethreads.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
  @NotEmpty(message = "DisplayName cannot be empty")
  private String displayName;
  @NotEmpty(message = "Email cannot be empty")
  private String email;
  @NotEmpty(message = "Password cannot be empty")
  private String password;
}
