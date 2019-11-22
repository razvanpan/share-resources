package com.intech.shareresources.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.intech.shareresources.dto.CategoryDto;
import com.intech.shareresources.service.CategoryService;

@RestController()
@RequestMapping(value = "/api")
@ControllerAdvice
public class CategoryController {

  private static final Logger LOG = LogManager.getLogger(CategoryController.class);
  @Autowired
  private CategoryService categoryService;


  @RequestMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public List<CategoryDto> allcategtories() {
    LOG.info(() -> "Getting categories ...");
    List<CategoryDto> categories = categoryService.getAllCategories();
    LOG.info(() -> "Resources retrieved successfully!");
    return categories;
  }

}
