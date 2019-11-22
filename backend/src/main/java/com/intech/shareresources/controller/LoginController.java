package com.intech.shareresources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import com.intech.shareresources.dto.LoginDto;
import com.intech.shareresources.dto.LoginResponseDto;
import com.intech.shareresources.security.JwtTokenUtil;
import com.intech.shareresources.security.JwtUser;
import com.intech.shareresources.service.LoginService;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

  // private static final Logger LOG = LogManager.getLogger(LoginController.class);

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private LoginService userDetailsService;

  @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public ResponseEntity<?> login(@RequestBody LoginDto loginDto, Device device)
      throws AuthenticationException {
    // Perform the security
    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-security so we can generate token
    final JwtUser userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());

    final String token = jwtTokenUtil.generateToken(userDetails, device);

    return ResponseEntity.ok(new LoginResponseDto(token, userDetails.getIdUser()));
  }

  @RequestMapping(value = "/checkIsAuthenticated", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public boolean checkIsAuthenticated() {
    return true;
  }
}
