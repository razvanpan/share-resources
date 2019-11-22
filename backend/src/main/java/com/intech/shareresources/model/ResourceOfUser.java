package com.intech.shareresources.model;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceOfUser {

  private long idResource;
  private String title;
  private Optional<String> type;
  private Optional<String> shortDescription;
  private String categoryName;
  private long idCategory;
  @NonNull
  private String[] tags;
  private Optional<byte[]> photo;
  
  

  
}
