package com.intech.shareresources.exceptions;

import java.util.List;
import com.intech.shareresources.model.UserBase;
import lombok.Getter;
import lombok.Setter;

public class TableException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -7584859393344859488L;
  /**
   * 
   */
  @Getter

  @Setter
  private List<UserBase> resourceId;

  public TableException(List<UserBase> users, String message) {
    super(message);
    this.resourceId = users;
  }
}
