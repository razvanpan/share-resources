package com.intech.shareresources.dto.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.intech.shareresources.controller.UserController;



public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  private Pattern pattern;
  private Matcher matcher;

  private static final Logger LOG = LogManager.getLogger(UserController.class);

  private static final String PASSWORD_PATTERN =
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";

  // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and
  // one special character
  public PasswordValidator() {
    pattern = Pattern.compile(PASSWORD_PATTERN);
  }

  /**
   * Validate password with regular expression
   * 
   * @param password password for validation
   * @return true valid password, false invalid password
   */
  public boolean validate(final String password) {
    pattern = Pattern.compile(PASSWORD_PATTERN);
    matcher = pattern.matcher(password);
    if (matcher.matches()) {
      LOG.info("Password accepted");
      return true;
    }
    LOG.info("Password denied");
    return false;

  }

  @Override
  public void initialize(ValidPassword arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {

    return (validate(password));

  }


}
