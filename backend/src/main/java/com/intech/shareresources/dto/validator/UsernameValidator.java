package com.intech.shareresources.dto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.intech.shareresources.controller.UserController;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

  private Pattern pattern;
  private Matcher matcher;

  private static final Logger LOG = LogManager.getLogger(UserController.class);

  private static final String USERNAME_PATTERN = "^[U0-9]{5,5}$";

  public UsernameValidator() {
    pattern = Pattern.compile(USERNAME_PATTERN);
  }

  /**
   * Validate password with regular expression
   * 
   * @param password password for validation
   * @return true valid password, false invalid password
   */
  public boolean validate(final String username) {
    pattern = Pattern.compile(USERNAME_PATTERN);
    matcher = pattern.matcher(username);
    if (matcher.matches()) {
      LOG.info("Username accepted");
      return true;
    }
    LOG.info("Username denied");
    return false;

  }

  @Override
  public void initialize(ValidUsername arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {

    return (validate(username));

  }

}
