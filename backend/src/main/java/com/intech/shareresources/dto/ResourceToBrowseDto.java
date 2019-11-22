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
public class ResourceToBrowseDto {

  private long idResource;
  private String title;
  private String fullNameUser;
  private long idUser;
  private Optional<String> type;
  private Optional<String> shortDescription;
  private String categoryName;
  @NonNull
  private String[] tags;
  private Optional<byte[]> photo;



}
