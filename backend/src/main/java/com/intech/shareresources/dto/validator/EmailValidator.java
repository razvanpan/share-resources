package com.intech.shareresources.dto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.intech.shareresources.controller.UserController;


public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
  private Pattern pattern;
  private Matcher matcher;
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  private static final Logger LOG = LogManager.getLogger(UserController.class);

  @Override
  public void initialize(ValidEmail constraintAnnotation) {}

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return (validateEmail(email));

  }

  private boolean validateEmail(String email) {
    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(email);
    if (matcher.matches()) {
      LOG.info("Email accepted");
      return true;
    }
    LOG.info("Email denied");
    return false;

  }
}
