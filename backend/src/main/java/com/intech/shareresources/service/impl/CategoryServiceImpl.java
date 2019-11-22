package com.intech.shareresources.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.CategoryDao;
import com.intech.shareresources.dto.CategoryDto;
import com.intech.shareresources.model.CategoryBase;
import com.intech.shareresources.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private CategoryDao categoryDao;
  private static final Logger LOG = LogManager.getLogger(ResourceServiceImpl.class);



  public List<CategoryDto> getAllCategories() {
    LOG.info(() -> "Looking for categories in the database...");
    List<CategoryBase> categories = categoryDao.getAllCategories();
    
    List<CategoryDto> categoriesDto =  Collections.emptyList();
    if (categories.isEmpty()) {
      LOG.error(() -> "No table content");
    } else {
      LOG.info(() -> "Mapping between object-List<CategoryDto>");
      categoriesDto =
          categories.stream().map(this::convertToCategoryDto).collect(Collectors.toList());
      LOG.info(() -> "The map has been made");
    }
    return categoriesDto;

  }

  private CategoryDto convertToCategoryDto(CategoryBase category) {
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setIdCategory(category.getIdCategory());
    categoryDto.setName(category.getName());

    return categoryDto;
  }

}
