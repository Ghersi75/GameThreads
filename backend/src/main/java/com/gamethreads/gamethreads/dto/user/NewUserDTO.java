package com.gamethreads.gamethreads.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
  private String displayName;
  private String email;
  private String password;
}
