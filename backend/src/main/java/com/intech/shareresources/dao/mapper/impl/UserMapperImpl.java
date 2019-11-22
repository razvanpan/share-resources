package com.intech.shareresources.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.mapper.UserMapper;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.model.UserPassword;

@Service
public class UserMapperImpl implements UserMapper {

  private static final Logger LOG = LogManager.getLogger(UserMapperImpl.class);

  @Override
  public UserBase convertToUserBase(ResultSet rs) throws SQLException {
    Optional<String> optShortDescription =
        rs.getString("shortdescription") != null ? Optional.of(rs.getString("shortdescription"))
            : Optional.empty();
    Optional<String> optphone =
        rs.getString("phone") != null ? Optional.of(rs.getString("phone")) : Optional.empty();
    LocalDateTime date1 = rs.getTimestamp("registerdate").toLocalDateTime();
    Optional<byte[]> optbytes =
        rs.getBytes("photo") != null ? Optional.of(rs.getBytes("photo")) : Optional.empty();

    return new UserBase(rs.getLong("iduser"), rs.getString("firstname"), rs.getString("lastname"),
        rs.getString("username"), rs.getString("password"), rs.getString("email"),
        optShortDescription, optphone, optbytes, date1, null);
  }


  @Override
  public UserBase convertToSingleUserBase(ResultSet rs) {
    try {
      if (rs.next()) {
        LOG.info(() -> "Converting User");

        return convertToUserBase(rs);
      }

    } catch (SQLException e) {
      LOG.error(() -> "User can not be converted ");
      e.printStackTrace();
    }
    LOG.debug(() -> "ConvertToUserSingle method was executed");
    return null;
  }

  public UserPassword convertToUserPassword(ResultSet rs) {
    try {
      if (rs.next()) {
        LOG.info(() -> "Converting user password");
        return new UserPassword(rs.getLong("iduser"), rs.getString("password"), null);
      }
    } catch (SQLException e) {
      LOG.error(() -> "User can not be converted ");
      e.printStackTrace();
    }
    LOG.debug(() -> "ConvertToUserSingle method was executed");
    return null;

  }
}
