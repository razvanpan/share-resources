package com.intech.shareresources.security;

import com.intech.shareresources.model.UserBase;

public final class JwtUserFactory {

  private JwtUserFactory() {}

  public static JwtUser create(UserBase user) {
    return new JwtUser(user.getUsername(), user.getPassword(), user.getIdUser());
  }

}
