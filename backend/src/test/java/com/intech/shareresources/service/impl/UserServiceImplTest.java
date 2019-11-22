package com.intech.shareresources.service.impl;

import static org.mockito.ArgumentMatchers.anyString;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.intech.shareresources.dao.UserDao;
import com.intech.shareresources.dto.UserNewPasswordDto;
import com.intech.shareresources.dto.UserPasswordDto;
import com.intech.shareresources.dto.UserPersonalDetailDto;
import com.intech.shareresources.dto.UserProfilePictureDto;
import com.intech.shareresources.dto.UserToEditDto;
import com.intech.shareresources.dto.UserToInsertDto;
import com.intech.shareresources.model.UserBase;



// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {


  @Mock
  private UserDao userDao;

  @Mock
  private PhotoServiceImpl photoService;

  @InjectMocks
  private UserServiceImpl userServiceImpl = new UserServiceImpl();


  @Test
  public void testChangePassword() throws FileNotFoundException, IOException {
    long id = 1;

    Mockito.when(userDao.changePassword(Mockito.any())).thenReturn(true);

    UserPasswordDto userPasswordDto = new UserPasswordDto(id, "oldPassword", "newPassword");

    Assert.assertTrue(userServiceImpl.changePassword(userPasswordDto));

  }

  @Test
  public void testChangeProfilePicture() {
    long id = 1;
    byte[] photo = new byte[] {1, 2, 3};
    Mockito.when(userDao.changeProfilePicture(Mockito.any())).thenReturn(true);

    UserProfilePictureDto userProfilePictureDto = new UserProfilePictureDto(id, Optional.of(photo));

    Assert.assertTrue(userServiceImpl.changeProfilePicture(userProfilePictureDto));
  }

  @Test
  public void testEditUser() {
    long id = 1;
    Optional<String> phone = Optional.of("0758895412");
    Optional<String> shortDescription = Optional.of("description");

    Mockito.when(userDao.editUser(Mockito.any())).thenReturn(true);

    UserToEditDto userToEditDto =
        new UserToEditDto(id, "firstName", "lastName", "email", shortDescription, phone);

    Assert.assertTrue(userServiceImpl.editUser(userToEditDto));
  }

  @Test
  public void testAddUserNullPhoto() {
    Optional<String> phone = Optional.of("0758895412");
    Optional<String> shortDescription = Optional.of("description");
    Mockito.when(userDao.addnewUser(Mockito.any())).thenReturn(true);
    Mockito.when(photoService.getUserAvatar()).thenReturn(new byte[] {1, 2, 3});
    UserToInsertDto userToInsertDto = new UserToInsertDto("firstName", "lastName", "username",
        "password", "email", shortDescription, phone);
    Assert.assertTrue(userServiceImpl.addUser(userToInsertDto));
  }

  @Test
  public void testChangeNewPassword() {
    long id = 1;
    Mockito.when(userDao.changeNewPassword(Mockito.any())).thenReturn(true);
    UserNewPasswordDto userNewPasswordDto = new UserNewPasswordDto(id, "newPassword");


    Assert.assertTrue(userServiceImpl.changeNewPassword(userNewPasswordDto));

  }

  @Test
  public void testGetUserByIdWithElements() {
    long id = 1;
    Optional<String> phone = Optional.of("0758895412");
    Optional<String> shortDescription = Optional.of("description");
    byte[] photo = new byte[] {1, 2, 3};

    UserBase userBase = new UserBase(id, "firstName", "lastName", "username", "password", "email",
        shortDescription, phone, Optional.of(photo), null, null);

    UserPersonalDetailDto userPersonalDetailDto = new UserPersonalDetailDto();
    userPersonalDetailDto.setIdUser(id);
    userPersonalDetailDto.setEmail("email");
    userPersonalDetailDto.setFirstName("firstName");
    userPersonalDetailDto.setLastName("lastName");
    userPersonalDetailDto.setPassword("password");
    userPersonalDetailDto.setUsername("username");

    userPersonalDetailDto.setPhone(phone);
    userPersonalDetailDto.setPhoto(Optional.of(photo));
    userPersonalDetailDto.setShortDescription(shortDescription);

    Mockito.when(userDao.findbyId(1)).thenReturn(userBase);

    UserPersonalDetailDto user = userServiceImpl.getUserById(1);
    Assert.assertEquals(userPersonalDetailDto, user);

  }

  @Test
  public void testGetUserById() {
    UserBase userBase = new UserBase();

    UserPersonalDetailDto userPersonalDetailDto = new UserPersonalDetailDto();
    Mockito.when(userDao.findbyId(1)).thenReturn(userBase);

    UserPersonalDetailDto user = userServiceImpl.getUserById(1);
    Assert.assertEquals(userPersonalDetailDto, user);

  }


}
