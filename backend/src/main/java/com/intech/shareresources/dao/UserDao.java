package com.intech.shareresources.dao;

import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.model.UserNewPassword;
import com.intech.shareresources.model.UserPassword;
import com.intech.shareresources.model.UserProfilePicture;
import com.intech.shareresources.model.UserToEdit;

public interface UserDao {
  UserBase findbyId(long id);

  boolean addnewUser(UserBase user);

  UserBase findByUsername(String username);

  boolean editUser(UserToEdit user);

  boolean changePassword(UserPassword user);

  boolean changeProfilePicture(UserProfilePicture user);

  UserBase findByEmail(String username, String email);

  boolean changeNewPassword(UserNewPassword user);
}
