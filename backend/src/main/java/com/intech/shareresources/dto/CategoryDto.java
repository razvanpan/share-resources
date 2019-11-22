package com.intech.shareresources.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

  private long idCategory;
  private String name;
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (idCategory ^ (idCategory >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    CategoryDto other = (CategoryDto) obj;
    if (idCategory != other.idCategory)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
  
  

}
