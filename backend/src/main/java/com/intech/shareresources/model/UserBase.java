package com.intech.shareresources.model;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBase {

  private long idUser;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String email;
  private Optional<String> shortDescription;
  private Optional<String> phone;
  private Optional<byte[]> photo;
  private LocalDateTime registerDate;
  private String resetToken;

  public boolean isPresent() {
    return true;
  }


}
