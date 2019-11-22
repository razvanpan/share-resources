package com.intech.shareresources.service;

import org.springframework.stereotype.Service;
import com.intech.shareresources.dto.UserNewPasswordDto;
import com.intech.shareresources.dto.UserPasswordDto;
import com.intech.shareresources.dto.UserPersonalDetailDto;
import com.intech.shareresources.dto.UserProfilePictureDto;
import com.intech.shareresources.dto.UserToEditDto;
import com.intech.shareresources.dto.UserToInsertDto;
import com.intech.shareresources.model.UserBase;

@Service
public interface UserService {

  UserPersonalDetailDto getUserById(int id);

  boolean addUser(UserToInsertDto userDto);

  boolean editUser(UserToEditDto userDto);

  boolean changePassword(UserPasswordDto userDto);

  boolean changeProfilePicture(UserProfilePictureDto userDto);

  UserBase findUserByEmailAndUsername(String username, String email);

  boolean changeNewPassword(UserNewPasswordDto userDto);
}
