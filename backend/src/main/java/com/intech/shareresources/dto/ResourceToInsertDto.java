package com.intech.shareresources.dto;

import java.util.Arrays;
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
public class ResourceToInsertDto {

  @NonNull
  private String title;
  private long idUser;
  private Optional<String> type;
  private Optional<String> shortDescription;
  private long idCategory;
  @NonNull
  private String[] tags;
  private Optional<byte[]> photo;

  @Override
  public String toString() {
    return "shortDescription=" + shortDescription + ", idCategory=" + idCategory + ", tags="
        + Arrays.toString(tags) + ", photo=" + Arrays.toString(photo.orElse(new byte[0])) + "]";
  }




}
