package com.intech.shareresources.dto;

import com.intech.shareresources.dto.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordDto {

  private long idUser;
  @ValidPassword
  private String oldPassword;
  @ValidPassword
  private String newPassword;

  public UserPasswordDto(long idUser, String oldPassword, String newPassword) {
    super();
    this.idUser = idUser;
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
  }


}
