package com.intech.shareresources.dto;

import com.intech.shareresources.dto.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNewPasswordDto {

  private long idUser;
  @ValidPassword
  private String newPassword;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (idUser ^ (idUser >>> 32));
    result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserNewPasswordDto other = (UserNewPasswordDto) obj;
    if (idUser != other.idUser)
      return false;
    if (newPassword == null) {
      if (other.newPassword != null)
        return false;
    } else if (!newPassword.equals(other.newPassword))
      return false;
    return true;
  }
  
  
}
