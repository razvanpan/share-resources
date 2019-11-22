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
public class ResourcePicture {

  private long idResource;
  private Optional<byte[]> photo;

}
