package com.intech.shareresources.exceptions;

import lombok.Getter;
import lombok.Setter;

public class UserException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -7584859393344859488L;
  /**
   * 
   */
  @Getter

  @Setter
  private int resourceId;

  public UserException(String message) {
    super(message);
  }
}


