package com.intech.shareresources.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.mapper.CategoryMapper;
import com.intech.shareresources.model.CategoryBase;

@Service
public class CategoryMapperImpl implements CategoryMapper {

  private static final Logger LOG = LogManager.getLogger(CategoryMapperImpl.class);

  public CategoryBase convertToCategoryBase(ResultSet rs) throws SQLException {
    return new CategoryBase(rs.getLong("idcategory"), rs.getString("name"));
  }

  public CategoryBase convertToSingleCategoryBase(ResultSet rs) {
    try {
      if (rs.next()) {
        LOG.info(() -> "Converting category");
        return convertToCategoryBase(rs);
      }
    } catch (SQLException e) {
      LOG.error(() -> "Category can not be converted ", e);
    }
    LOG.debug(() -> "ConvertToCategorySingle method was executed");
    return null;

  }
}
