package com.intech.shareresources.dto;

import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcePictureDto {
  private long idResource;
  private Optional<byte[]> photo;

}
