package com.intech.shareresources.dto;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResourceToEditDto {

  private long idResource;
  @NonNull
  private String title;
  private Optional<String> type;
  private Optional<String> shortDescription;
  private long idCategory;
  @NonNull
  private String[] tags;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (idCategory ^ (idCategory >>> 32));
    result = prime * result + (int) (idResource ^ (idResource >>> 32));
    result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
    result = prime * result + Arrays.hashCode(tags);
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ResourceToEditDto other = (ResourceToEditDto) obj;
    if (idCategory != other.idCategory)
      return false;
    if (idResource != other.idResource)
      return false;
    if (shortDescription == null) {
      if (other.shortDescription != null)
        return false;
    } else if (!shortDescription.equals(other.shortDescription))
      return false;
    if (!Arrays.equals(tags, other.tags))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    return true;
  }



}
