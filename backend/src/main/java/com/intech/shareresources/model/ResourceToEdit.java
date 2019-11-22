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
public class ResourceToEdit {

  private long idResource;
  private long idCategory;
  private String title;
  private Optional<String> type;
  private Optional<String> shortDescription;
  private String[] tags;

}
