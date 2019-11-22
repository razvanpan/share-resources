package com.intech.shareresources.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.intech.shareresources.controller.UserController;
import com.intech.shareresources.dao.UserDao;
import com.intech.shareresources.dao.mapper.UserMapper;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.model.UserNewPassword;
import com.intech.shareresources.model.UserPassword;
import com.intech.shareresources.model.UserProfilePicture;
import com.intech.shareresources.model.UserToEdit;

@Service
public class UserDaoImpl implements UserDao {
  private static final Logger LOG = LogManager.getLogger(UserController.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private UserMapper usrMapper;

  public UserBase findbyId(long id) {
    String sql = "SELECT * FROM \"user\" WHERE iduser= ?";
    UserBase user = jdbcTemplate.query(sql, new Object[] {id},
        (ResultSet rs) -> usrMapper.convertToSingleUserBase(rs));
    LOG.info("User with coresponding ID was found!");
    return user;
  }


  @Override
  public boolean changePassword(UserPassword user) {
    LOG.info(() -> "Updating user password in dao");
    String sql = "SELECT * from \"user\" where iduser = ?";

    UserPassword userAux = jdbcTemplate.query(sql, new Object[] {user.getIdUser()},
        (ResultSet rs) -> usrMapper.convertToUserPassword(rs));

    if (BCrypt.checkpw(user.getOldPassword(), userAux.getOldPassword())) {
      int addRows = jdbcTemplate.update("update \"user\" set password = ? where iduser = ?",
          user.getNewPassword(), user.getIdUser());
      if (addRows == 0) {
        LOG.info(() -> "Password can not be changed");
        return false;
      } else {
        LOG.info(() -> "Password was changed");
        return true;
      }
    } else {
      LOG.info(() -> " The old password is wrong!");
      return false;
    }
  }

  @Override
  public boolean editUser(UserToEdit user) {
    LOG.info(() -> "Adding user");
    int addRows = jdbcTemplate.update(
        "update \"user\" set firstname = ?, lastname = ?, email = ?, shortdescription = ?, phone = ? where iduser = ?",
        user.getFirstName(), user.getLastName(), user.getEmail(),
        user.getShortDescription().orElse(null), user.getPhone().orElse(null), user.getIdUser());

    if (addRows == 0) {
      LOG.info(() -> "User can not be added");
      return false;
    } else {
      LOG.info(() -> "User was added");
      return true;
    }
  }


  public boolean addnewUser(UserBase user) {
    LOG.info("Adding user");
    int addROws = jdbcTemplate.update(
        "INSERT INTO \"user\" (firstname,lastname, username, password, email, shortdescription, phone, photo, registerdate) VALUES (?,?,?,?,?,?,?,?,?)",
        user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),
        user.getEmail(), user.getShortDescription().orElse(null), user.getPhone().orElse(null),
        user.getPhoto().orElse(null), Timestamp.valueOf(user.getRegisterDate()));
    if (addROws == 0) {
      LOG.info("User can not be added");
      return false;
    } else {
      LOG.info("User was added");
      return true;
    }
  }


  @Override
  public UserBase findByUsername(String username) {
    LOG.info("Searching for user in dao");
    String sql = "SELECT * FROM \"user\" WHERE username= ?";
    UserBase user = jdbcTemplate.query(sql, new Object[] {username},
        (ResultSet rs) -> usrMapper.convertToSingleUserBase(rs));
    LOG.info("User with coresponding ID was found!");
    return user;
  }


  @Override
  public boolean changeProfilePicture(UserProfilePicture user) {
    LOG.info("Updating user picture in dao");
    int addRows = jdbcTemplate.update("update \"user\" set photo = ? where iduser = ?",
        user.getPhoto().orElse(null), user.getIdUser());
    if (addRows == 0) {
      LOG.info(() -> "Picture can not be changed");
      return false;
    } else {
      LOG.info(() -> "Picture was changed");
      return true;
    }
  }



  @Override
  public UserBase findByEmail(String username, String email) {
    String sql = "SELECT * FROM \"user\" WHERE username= ? AND email=?";
    UserBase user = jdbcTemplate.query(sql, new Object[] {username, email},
        (ResultSet rs) -> usrMapper.convertToSingleUserBase(rs));
    LOG.info("User with coresponding username and email was found!");
    return user;
  }


  @Override
  public boolean changeNewPassword(UserNewPassword user) {
    String sql = "SELECT * from \"user\" where iduser = ?";

    UserPassword userAux = jdbcTemplate.query(sql, new Object[] {user.getIdUser()},
        (ResultSet rs) -> usrMapper.convertToUserPassword(rs));

    int addRows = jdbcTemplate.update("update \"user\" set password = ? where iduser = ?",
        user.getNewPassword(), user.getIdUser());
    if (addRows == 0) {
      LOG.info(() -> "Password can not be changed");
      return false;
    } else {
      LOG.info(() -> "Password was changed");
      return true;
    }

  }

}
