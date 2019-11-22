package com.intech.shareresources.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.intech.shareresources.dao.impl.UserDaoImpl;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.model.UserPassword;
import com.intech.shareresources.model.UserToEdit;
import com.intech.shareresources.service.impl.PhotoServiceImpl;
import com.intech.shareresources.test.config.SharedResourcesTestConfig;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)

@ContextConfiguration(classes = {SharedResourcesTestConfig.class})


@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:testDbScript/initTestDb.sql"),
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:testDbScript/insertUserDb.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
        scripts = "classpath:testDbScript/cleanDb.sql")})

public class UserDaoImplTest {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private UserDaoImpl userDaoImpl;



  @Test
  public void testFindById() throws FileNotFoundException, IOException {
    UserBase user = userDaoImpl.findbyId(1);
    Assert.assertEquals(user.getIdUser(), 1);
    Assert.assertEquals(user.getFirstName(), "lavinia");
    Assert.assertEquals(user.getLastName(), "Belletristick");
    Assert.assertEquals(user.getUsername(), "U1234");
    Assert.assertEquals(user.getPassword(), "blabla12$FF");
    Assert.assertEquals(user.getEmail(), "Trainings@in-tech.com");
    Assert.assertEquals(user.getShortDescription(), "Description!");
    Assert.assertEquals(user.getPhone(), "0757393333");
    Assert.assertEquals(user.getPhoto(), null);
    Assert.assertEquals(user.getRegisterDate(), "1999-01-08 04:05:06");
    // Assert.assertEquals(user.getResetToken(),
    // "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVNDMyMSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwNzEyNzU4NjY5NywiZXhwIjoxNTA3MTM0Nzg2fQ.zezXxJl7zfGMKtDuNYXnCbMBGUlYV7TSgLykG2-tufEzx0RqGfarEFmioxj0-b6FKJj2snwIRB2CkR8woO8yPg");

  }

  @Test
  public void testFindByEmail() throws FileNotFoundException, IOException {
    UserBase user = userDaoImpl.findByEmail("u1234", "Trainings@in-tech.com");
    Assert.assertEquals(user.getIdUser(), 1);
    Assert.assertEquals(user.getFirstName(), "lavinia");
    Assert.assertEquals(user.getLastName(), "Belletristick");
    Assert.assertEquals(user.getUsername(), "U1234");
    Assert.assertEquals(user.getPassword(), "blabla12$FF");
    Assert.assertEquals(user.getEmail(), "Trainings@in-tech.com");
    Assert.assertEquals(user.getShortDescription(), "Description!");
    Assert.assertEquals(user.getPhone(), "0757393333");
    Assert.assertEquals(user.getPhoto(), null);
    Assert.assertEquals(user.getRegisterDate(), "1999-01-08 04:05:06");
    // Assert.assertEquals(user.getResetToken(),
    // "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVNDMyMSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwNzEyNzU4NjY5NywiZXhwIjoxNTA3MTM0Nzg2fQ.zezXxJl7zfGMKtDuNYXnCbMBGUlYV7TSgLykG2-tufEzx0RqGfarEFmioxj0-b6FKJj2snwIRB2CkR8woO8yPg");

  }

  @Test
  public void testAddNewUser() throws FileNotFoundException, IOException {

    jdbcTemplate.query("Select * from user where id=1", new ResultSetExtractor<Object>() {

      @Override
      public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        Assert.assertEquals(rs.getString("id"), "1");
        Assert.assertEquals(rs.getString("firstName"), "lavinia");
        Assert.assertEquals(rs.getString("lastName"), "Belletristick");
        Assert.assertEquals(rs.getString("username"), "U1234");
        Assert.assertEquals(rs.getString("password"), "blabla12$FF");
        Assert.assertEquals(rs.getString("email"), "Trainings@in-tech.com");
        Assert.assertEquals(rs.getString("shortDescription"), "Description!");
        Assert.assertEquals(rs.getString("phone"), "0757393333");
        Assert.assertEquals(rs.getBytes("photo"), null);
        Assert.assertEquals(rs.getDate("registerDate"), "1999-01-08 04:05:06");
        // Assert.assertEquals(rs.getString("resetToken"),
        // "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVNDMyMSIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwNzEyNzU4NjY5NywiZXhwIjoxNTA3MTM0Nzg2fQ.zezXxJl7zfGMKtDuNYXnCbMBGUlYV7TSgLykG2-tufEzx0RqGfarEFmioxj0-b6FKJj2snwIRB2CkR8woO8yPg");

        return null;
      }
    });
  }

}
