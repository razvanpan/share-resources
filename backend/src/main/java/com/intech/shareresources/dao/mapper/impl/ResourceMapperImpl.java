package com.intech.shareresources.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.mapper.CategoryMapper;
import com.intech.shareresources.dao.mapper.ResourceMapper;
import com.intech.shareresources.dao.mapper.UserMapper;
import com.intech.shareresources.model.CategoryBase;
import com.intech.shareresources.model.ResourceComplex;
import com.intech.shareresources.model.ResourceOfUser;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.util.JsonUtil;

@Service
public class ResourceMapperImpl implements ResourceMapper {

  private static final Logger LOG = LogManager.getLogger(ResourceMapperImpl.class);

  @Autowired
  private CategoryMapper catMap;
  @Autowired
  private UserMapper usrMap;
  @Autowired
  private JsonUtil jsonUtil;

  @Override
  public ResourceOfUser convertToResourceOfUser(ResultSet rs) {
    try {
      LOG.info(() -> "Converting all resources");
      LOG.debug(() -> "ConvertToResource method in progress");

      Optional<String> optshortDescription =
          rs.getString("shortdescription") != null ? Optional.of(rs.getString("shortdescription"))
              : Optional.empty();

      Optional<String> optionalType =
          rs.getString("type") != null ? Optional.of(rs.getString("type")) : Optional.empty();

      Optional<byte[]> optPhoto =
          rs.getString("photo") != null ? Optional.of(rs.getBytes("photo")) : Optional.empty();

      CategoryBase category = catMap.convertToCategoryBase(rs);

      String[] tags = jsonUtil.convertFromJson(rs.getString("tags"), String[].class);

      return new ResourceOfUser(rs.getLong("idresource"), rs.getString("title"), optionalType,
          optshortDescription, category.getName(), category.getIdCategory(), tags, optPhoto);
    } catch (SQLException e) {
      LOG.error(() -> "Resource can not be converted ");
      e.printStackTrace();
    }
    LOG.debug(() -> "ConvertToResource method was executed");
    return null;

  }


  @Override
  public ResourceComplex convertToResourceComplex(ResultSet rs) {
    try {
      LOG.info(() -> "Converting all resources");
      LOG.debug(() -> "ConvertToResource method in progress");

      Optional<String> optshortDescription =
          rs.getString("shortdescription") != null ? Optional.of(rs.getString("shortdescription"))
              : Optional.empty();

      Optional<String> optionalType =
          rs.getString("type") != null ? Optional.of(rs.getString("type")) : Optional.empty();

      Optional<byte[]> optPhoto =
          rs.getString("photo") != null ? Optional.of(rs.getBytes("photo")) : Optional.empty();
      LocalDateTime date1 = rs.getTimestamp("registerdate").toLocalDateTime();

      UserBase user = usrMap.convertToUserBase(rs);
      CategoryBase category = catMap.convertToCategoryBase(rs);

      String[] tags = jsonUtil.convertFromJson(rs.getString("tags"), String[].class);

      return new ResourceComplex(rs.getLong("idresource"), user, category, rs.getString("title"),
          optionalType, optPhoto, optshortDescription, tags, date1);
    } catch (SQLException e) {
      LOG.error(() -> "Resource can not be converted ");
      e.printStackTrace();
    }
    LOG.debug(() -> "ConvertToResource method was executed");
    return null;

  }

  @Override
  public ResourceComplex convertToSingleResourceComplex(ResultSet rs) {
    try {
      if (rs.next())
        return convertToResourceComplex(rs);
    } catch (SQLException e) {
      LOG.error(() -> "Resource can not be converted ");
      e.printStackTrace();
    }
    LOG.debug(() -> "ConvertToResource method was executed");
    return null;

  }
}
