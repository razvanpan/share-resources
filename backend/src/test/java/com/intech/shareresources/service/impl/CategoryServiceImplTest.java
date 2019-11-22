package com.intech.shareresources.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.intech.shareresources.dao.CategoryDao;
import com.intech.shareresources.dto.CategoryDto;
import com.intech.shareresources.model.CategoryBase;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
  @Mock
  private CategoryDao categroyDao;
  @InjectMocks
  private CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();

  @Test
  public void testGetAllResourceEmptyList(){
    Mockito.when(categroyDao.getAllCategories()).thenReturn(Collections.emptyList());
    List<CategoryDto> category = categoryServiceImpl.getAllCategories();
    Assert.assertTrue(category.isEmpty()); 
  }
  
  @Test
  public void testGetAllResourceConversion(){
    CategoryBase catBase = new CategoryBase(1, "test");
    CategoryDto catDto = new CategoryDto();
    catDto.setIdCategory(1);
    catDto.setName("test");
    
    Mockito.when(categroyDao.getAllCategories()).thenReturn(Arrays.asList(catBase));
    List<CategoryDto> category = categoryServiceImpl.getAllCategories();
    Assert.assertEquals(catDto,category.get(0));
  }
  
}
