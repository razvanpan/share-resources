package com.intech.shareresources.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intech.shareresources.controller.UserController;
import com.intech.shareresources.dao.UserDao;
import com.intech.shareresources.dto.UserNewPasswordDto;
import com.intech.shareresources.dto.UserPasswordDto;
import com.intech.shareresources.dto.UserPersonalDetailDto;
import com.intech.shareresources.dto.UserProfilePictureDto;
import com.intech.shareresources.dto.UserToEditDto;
import com.intech.shareresources.dto.UserToInsertDto;
import com.intech.shareresources.exceptions.UserException;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.model.UserNewPassword;
import com.intech.shareresources.model.UserPassword;
import com.intech.shareresources.model.UserProfilePicture;
import com.intech.shareresources.model.UserToEdit;
import com.intech.shareresources.service.PhotoService;
import com.intech.shareresources.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOG = LogManager.getLogger(UserController.class);
  @Autowired
  private UserDao userDao;
  @Autowired
  private PhotoService photoService;


  @Override
  public UserPersonalDetailDto getUserById(int id) {

    LOG.info(() -> "Looking for the user in the database...");
    UserBase userById = userDao.findbyId(id);
    if (userById == null) {
      throw new UserException("User with id: " + id + " not found!");
    }
    LOG.info(() -> "Converting user for usability...");
    UserPersonalDetailDto userDto = convertToUserDto(userById);
    LOG.info(() -> "Retrieving user...");
    return userDto;

  }

  @Override
  public boolean changePassword(UserPasswordDto userDto) {
    LOG.info(() -> "Converting from dto to model ");

    UserPassword user = new UserPassword();
    user.setIdUser(userDto.getIdUser());
    user.setOldPassword(userDto.getOldPassword());
    user.setNewPassword(userDto.getNewPassword());


    return userDao.changePassword(user);
  }

  @Override
  public boolean changeProfilePicture(UserProfilePictureDto userDto) {
    LOG.info(() -> "Converting to  UserProfilePicture ");
    UserProfilePicture user = new UserProfilePicture();
    user.setIdUser(userDto.getIdUser());
    user.setPhoto(userDto.getPhoto());
    return userDao.changeProfilePicture(user);
  }


  @Override
  public boolean editUser(UserToEditDto userDto) {
    LOG.info(() -> "Updating user ");
    LOG.debug(() -> "Updating user in Service implements");

    UserToEdit user = new UserToEdit();

    user.setIdUser(userDto.getIdUser());
    user.setEmail(userDto.getEmail());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPhone(userDto.getPhone());
    user.setShortDescription(userDto.getShortDescription());
    return userDao.editUser(user);

  }

  @Override
  public boolean addUser(UserToInsertDto userDto) {
    LOG.info(() -> "Adding user ");
    LOG.debug(() -> "Adding user in Service implements");

    UserBase user = new UserBase();
    user.setEmail(userDto.getEmail());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPassword(userDto.getPassword());
    user.setPhone(userDto.getPhone());
    user.setPhoto(Optional.of(photoService.getUserAvatar()));
    user.setShortDescription(userDto.getShortDescription());
    user.setUsername(userDto.getUsername());
    user.setRegisterDate(LocalDateTime.now());

    return userDao.addnewUser(user);
  }

  private UserPersonalDetailDto convertToUserDto(UserBase u) {
    LOG.info(() -> "Converting a Userbase into a UserDto");
    UserPersonalDetailDto uDto = new UserPersonalDetailDto();
    uDto.setIdUser(u.getIdUser());
    uDto.setEmail(u.getEmail());
    uDto.setPassword(u.getPassword());
    uDto.setFirstName(u.getFirstName());
    uDto.setLastName(u.getLastName());
    uDto.setShortDescription(u.getShortDescription());
    uDto.setRegisterDate(u.getRegisterDate());
    uDto.setUsername(u.getUsername());
    uDto.setPhone(u.getPhone());
    uDto.setPhoto(u.getPhoto());
    LOG.info(() -> "UserDto returned");
    return uDto;
  }

  @Override
  public UserBase findUserByEmailAndUsername(String username, String email) {
    return userDao.findByEmail(username, email);
  }


  @Override
  public boolean changeNewPassword(UserNewPasswordDto userDto) {
    LOG.info(() -> "Converting from dto to model ");

    UserNewPassword user = new UserNewPassword();
    user.setIdUser(userDto.getIdUser());
    user.setNewPassword(userDto.getNewPassword());


    return userDao.changeNewPassword(user);
  }


}
