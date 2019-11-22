package com.intech.shareresources.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.intech.shareresources.model.CategoryBase;

public interface CategoryMapper {

  CategoryBase convertToCategoryBase(ResultSet rs) throws SQLException;

  CategoryBase convertToSingleCategoryBase(ResultSet rs);
}
