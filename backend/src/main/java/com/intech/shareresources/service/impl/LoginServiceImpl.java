package com.intech.shareresources.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.UserDao;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.security.JwtUser;
import com.intech.shareresources.security.JwtUserFactory;
import com.intech.shareresources.service.LoginService;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UserDao userDao;

  @Override
  public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
    UserBase user = userDao.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(
          String.format("No user found with username '%s'.", username));
    } else {
      return JwtUserFactory.create(user);
    }
  }
}
