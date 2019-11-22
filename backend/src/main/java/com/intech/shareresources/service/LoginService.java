package com.intech.shareresources.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.intech.shareresources.security.JwtUser;

public interface LoginService extends UserDetailsService {

  JwtUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
