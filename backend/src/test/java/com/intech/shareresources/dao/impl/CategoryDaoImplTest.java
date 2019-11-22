package com.intech.shareresources.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.intech.shareresources.model.CategoryBase;
import com.intech.shareresources.test.config.SharedResourcesTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SharedResourcesTestConfig.class})

@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:testDbScript/initTestDb.sql"),
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts="classpath:testDbScript/insertCategories.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
        scripts = "classpath:testDbScript/cleanDb.sql")})
public class CategoryDaoImplTest {

  @Autowired
  private CategoryDaoImpl categoryDaoImpl;


  @Test
  public void testFindByCategoryId() throws FileNotFoundException, IOException {
    CategoryBase category = categoryDaoImpl.findbyCategoryId(1);
    Assert.assertEquals(category.getIdCategory(), 1);
    Assert.assertEquals(category.getName(), "Technical Book");
  }

  @Test
  public <T> void testGetAllCategoriesCount() throws FileNotFoundException, IOException {
    List<CategoryBase> categories = categoryDaoImpl.getAllCategories();
    Assert.assertEquals(5, categories.size());

  }

}
