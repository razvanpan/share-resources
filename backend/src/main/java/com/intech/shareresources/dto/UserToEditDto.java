package com.intech.shareresources.dto;

import java.util.Optional;
import com.intech.shareresources.dto.validator.ValidEmail;
import com.intech.shareresources.dto.validator.ValidPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserToEditDto {

  private long idUser;
  private String firstName;
  private String lastName;
  @ValidEmail
  private String email;
  private Optional<String> shortDescription;
  @ValidPhone
  private Optional<String> phone;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + (int) (idUser ^ (idUser >>> 32));
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
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
    UserToEditDto other = (UserToEditDto) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (idUser != other.idUser)
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (phone == null) {
      if (other.phone != null)
        return false;
    } else if (!phone.equals(other.phone))
      return false;
    if (shortDescription == null) {
      if (other.shortDescription != null)
        return false;
    } else if (!shortDescription.equals(other.shortDescription))
      return false;
    return true;
  }
  
  
}
