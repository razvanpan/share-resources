package com.intech.shareresources.model;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePicture {

  private long idUser;
  private Optional<byte[]> photo;

}
