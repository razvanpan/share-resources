package com.intech.shareresources.dto.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.intech.shareresources.controller.UserController;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

  private Pattern pattern;
  private Matcher matcher;

  private static final Logger LOG = LogManager.getLogger(UserController.class);

  private static final String PHONE_PATTERN = "\\d{10}";

  public PhoneValidator() {
    pattern = Pattern.compile(PHONE_PATTERN);
  }

  /**
   * Validate password with regular expression
   * 
   * @param password password for validation
   * @return true valid password, false invalid password
   */
  public boolean validate(final String phone) {
    pattern = Pattern.compile(PHONE_PATTERN);
    matcher = pattern.matcher(phone);
    if (matcher.matches()) {
      LOG.info("Phone number accepted");
      return true;
    }
    LOG.info("Phone number denied");
    return false;

  }

  @Override
  public void initialize(ValidPhone arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isValid(String phone, ConstraintValidatorContext context) {

    return (validate(phone));

  }


}
