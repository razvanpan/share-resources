package com.intech.shareresources.model;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceComplex {
  private long idResource;
  private UserBase user;
  private CategoryBase category;
  private String title;
  private Optional<String> type;
  private Optional<byte[]> photo;
  private Optional<String> shortDescription;
  private String[] tags;
  private LocalDateTime registerDate;

}
