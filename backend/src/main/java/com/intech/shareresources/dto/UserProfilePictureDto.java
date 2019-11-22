package com.intech.shareresources.dto;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilePictureDto {
  private long idUser;
  private Optional<byte[]> photo;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (idUser ^ (idUser >>> 32));
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
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
    UserProfilePictureDto other = (UserProfilePictureDto) obj;
    if (idUser != other.idUser)
      return false;
    if (photo == null) {
      if (other.photo != null)
        return false;
    } else if (!photo.equals(other.photo))
      return false;
    return true;
  }

  
}
