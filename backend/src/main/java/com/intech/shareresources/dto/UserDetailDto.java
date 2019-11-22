package com.intech.shareresources.dto;

import java.util.Optional;
import com.intech.shareresources.dto.validator.ValidEmail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailDto {

  private long idUser;
  private String firstName;
  private String lastName;
  @ValidEmail
  private String email;
  private Optional<String> shortDescription;
  private Optional<String> phone;
  private Optional<byte[]> photo;

}
