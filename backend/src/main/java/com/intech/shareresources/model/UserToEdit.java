package com.intech.shareresources.model;

import java.util.Optional;
import com.intech.shareresources.dto.validator.ValidEmail;
import com.intech.shareresources.dto.validator.ValidPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToEdit {
  private long idUser;
  private String firstName;
  private String lastName;
  @ValidEmail
  private String email;
  private Optional<String> shortDescription;
  @ValidPhone
  private Optional<String> phone;
}
