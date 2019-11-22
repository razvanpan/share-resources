package com.intech.shareresources.model;

import com.intech.shareresources.dto.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword {

  private long idUser;
  @ValidPassword
  private String oldPassword;
  @ValidPassword
  private String newPassword;

}
