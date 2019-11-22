package com.intech.shareresources.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.intech.shareresources.dto.UserPasswordDto;
import com.intech.shareresources.dto.UserPersonalDetailDto;
import com.intech.shareresources.dto.UserProfilePictureDto;
import com.intech.shareresources.dto.UserToEditDto;
import com.intech.shareresources.dto.UserToInsertDto;
import com.intech.shareresources.exceptions.ApiError;
import com.intech.shareresources.exceptions.ExceptionResponse;
import com.intech.shareresources.exceptions.UserException;
import com.intech.shareresources.service.UserService;

/**
 * The type Main controller.
 */
@RestController()
@RequestMapping(value = "/api")
@ControllerAdvice
public class UserController {
  private static final Logger LOG = LogManager.getLogger(UserController.class);

  @Autowired
  private UserService userService;


  @RequestMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  @ResponseBody
  public UserPersonalDetailDto userById(@PathVariable("id") int id) {
    LOG.info(() -> "Commencing search...");
    UserPersonalDetailDto userById = userService.getUserById(id);
    LOG.info(() -> "User retrieved!");
    return userById;
  }

  @RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)

  public boolean insertUser(@Valid @RequestBody UserToInsertDto userDto) {
    LOG.info(() -> "Creating new user...");
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(userDto.getPassword());
    userDto.setPassword(hashedPassword);
    boolean insert = userService.addUser(userDto);
    LOG.info(() -> "User created successfully!");
    return insert;
  }


  @RequestMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
  public boolean editResource(@PathVariable("id") int id,
      @Valid @RequestBody UserToEditDto userDto) {
    LOG.info(() -> "Updating new user...");
    userDto.setIdUser(id);
    boolean edit = userService.editUser(userDto);
    LOG.info(() -> "User updated successfully!");
    return edit;
  }

  @RequestMapping(value = "/user/{id}/password", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
  public boolean changePassword(@PathVariable("id") int id,
      @Valid @RequestBody UserPasswordDto userDto) {
    LOG.info(() -> "Updating new password...");
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(userDto.getNewPassword());
    userDto.setNewPassword(hashedPassword);
    userDto.setIdUser(id);
    boolean edit = userService.changePassword(userDto);
    LOG.info(() -> "The procces is finished!");
    return edit;
  }

  @RequestMapping(value = "/user/{id}/picture", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
  public boolean changeProfilePicture(@PathVariable("id") int id,
      @Valid @RequestBody UserProfilePictureDto userDto) {
    LOG.info(() -> "Updating new picture...");
    userDto.setIdUser(id);
    boolean edit = userService.changeProfilePicture(userDto);
    LOG.info(() -> "User picture updated successfully!");
    return edit;
  }

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ExceptionResponse> resourceNotFound(UserException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode("404");
    response.setMessage(ex.getMessage());
    LOG.error(() -> "User not found!");
    return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, WebRequest request) {
    String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    LOG.error(() -> "Invalid input type");
    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

}

