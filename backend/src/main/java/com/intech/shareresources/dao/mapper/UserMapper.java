package com.intech.shareresources.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.model.UserPassword;

public interface UserMapper {
  UserBase convertToUserBase(ResultSet rs) throws SQLException;

  UserBase convertToSingleUserBase(ResultSet rs);

  UserPassword convertToUserPassword(ResultSet rs);
}
