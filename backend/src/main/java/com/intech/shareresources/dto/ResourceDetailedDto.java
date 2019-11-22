package com.intech.shareresources.dto;

import java.util.Optional;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ResourceDetailedDto {

  private long idResource;
  private String title;
  private UserDetailDto userDetail;
  private Optional<String> type;
  private Optional<String> shortDescription;
  private String categoryName;
  @NonNull
  private String[] tags;
  private Optional<byte[]> photo;
  private Optional<String> phone;
  private String email;
  private String fullNameUser;
  
}


