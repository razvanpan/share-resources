package com.intech.shareresources.dto;

import java.time.LocalDateTime;
import java.util.Optional;
import com.intech.shareresources.dto.validator.ValidEmail;
import com.intech.shareresources.dto.validator.ValidPassword;
import com.intech.shareresources.dto.validator.ValidPhone;
import com.intech.shareresources.dto.validator.ValidUsername;
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
public class UserPersonalDetailDto {

  private long idUser;
  private String firstName;
  private String lastName;
  @ValidUsername
  private String username;
  @ValidPassword
  private String password;
  @ValidEmail
  private String email;
  private Optional<String> shortDescription;
  @ValidPhone
  private Optional<String> phone;
  private Optional<byte[]> photo;
  private LocalDateTime registerDate;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + (int) (idUser ^ (idUser >>> 32));
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
    result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
    result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    UserPersonalDetailDto other = (UserPersonalDetailDto) obj;
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
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (phone == null) {
      if (other.phone != null)
        return false;
    } else if (!phone.equals(other.phone))
      return false;
    if (photo == null) {
      if (other.photo != null)
        return false;
    } else if (!photo.equals(other.photo))
      return false;
    if (registerDate == null) {
      if (other.registerDate != null)
        return false;
    } else if (!registerDate.equals(other.registerDate))
      return false;
    if (shortDescription == null) {
      if (other.shortDescription != null)
        return false;
    } else if (!shortDescription.equals(other.shortDescription))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }




 


}
