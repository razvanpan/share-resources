package com.intech.shareresources.controller;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.intech.shareresources.dto.UserNewPasswordDto;
import com.intech.shareresources.dto.UserPasswordDto;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.service.EmailService;
import com.intech.shareresources.service.UserService;
import eu.bitwalker.useragentutils.UserAgent;

@RestController
@RequestMapping(value = "/api")
public class PasswordController {
  private static final Logger LOG = LogManager.getLogger(ResourceController.class);
  @Autowired
  private EmailService emailService;
  @Autowired
  private UserService userService;
  private static SecureRandom random = new SecureRandom();
  private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
  private static final String NUMERIC = "0123456789";
  private static final String SPECIAL_CHARS = "$@$!%*?&";
  @RequestMapping(value = "/forgot", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public boolean processForgotPasswordForm(@RequestParam("username") String username,
      @RequestParam("email") String email, HttpServletRequest request) {

    // Lookup user in database by e-mail
    UserBase optional = userService.findUserByEmailAndUsername(username, email);
    if (!optional.isPresent()) {
      LOG.error(() -> "We didn't find an account for that e-mail address.");
      return false;
    } else {
      // Generate and update password
//      char[] possibleCharacters =
//          (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$@$!%*?&"))
//              .toCharArray();
      int len = 15;
      String password = generatePassword(len, ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC);
      UserNewPasswordDto userDto = new UserNewPasswordDto();
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String hashedPassword = passwordEncoder.encode(password);
      userDto.setNewPassword(hashedPassword);
      userDto.setIdUser(optional.getIdUser());
      boolean edit = userService.changeNewPassword(userDto);
      LOG.info(() -> "User password updated successfully!");
      // Scan OS and browser data
      String OS = System.getProperty("os.name");
      UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String formatDateTime = now.format(formatter);
      // Email message
      SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
      passwordResetEmail.setFrom("dummysharedresrouces@gmail.com");
      passwordResetEmail.setTo(optional.getEmail());
      passwordResetEmail.setSubject("Password Reset Request");
      passwordResetEmail.setText("Hi " + optional.getFirstName()
          + ",\r\n\r\nYou recently requested to reset your password. The request has been made from a "
          + OS + " Device, using " + userAgent.getBrowser().getName() + " Version "
          + userAgent.getBrowserVersion() + " on " + formatDateTime
          + ".\r\nYou newly generated password can be used below:\r\n\r\n" + password
          + "\r\n\r\nThanks,\r\n"
          + "The SharedResources Team\r\n\r\n© 2017 SharedResources. All rights reserved.\r\n\r\n\r\n"
          + "in-tech engineering services SRL\r\n");
      emailService.send(passwordResetEmail);
    }

    return true;

  }
  public static String generatePassword(int len, String dic) {
    String result = "";
    for (int i = 0; i < len; i++) {
        int index = random.nextInt(dic.length());
        result += dic.charAt(index);
    }
    return result;
 }
}

