package com.intech.shareresources.dao;

import java.util.List;
import com.intech.shareresources.model.CategoryBase;


public interface CategoryDao {

  CategoryBase findbyCategoryId(long id);

  List<CategoryBase> getAllCategories();
}
