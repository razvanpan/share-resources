package com.intech.shareresources.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.intech.shareresources.dao.impl.ResourceDaoImpl;
import com.intech.shareresources.model.ResourceComplex;
import com.intech.shareresources.model.ResourceOfUser;
import com.intech.shareresources.model.ResourceToEdit;
import com.intech.shareresources.model.ResourceToInsert;
import com.intech.shareresources.service.impl.PhotoServiceImpl;
import com.intech.shareresources.test.config.SharedResourcesTestConfig;

@ContextConfiguration(classes = {SharedResourcesTestConfig.class})


@SqlGroup({
  @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
      scripts = "classpath:testDbScript/initTestDb.sql"),
  @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
      statements="INSERT INTO public.resource (user, category, title, type, photo, shortdescription, tags, registerdate) VALUES ('lavinia', 'Belletristick', 'title', 'type', 'null','Description!','tags','1999-01-08 04:05:06')"),
  @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
      scripts = "classpath:testDbScript/cleanDb.sql")})

@RunWith(MockitoJUnitRunner.class)
public class ResourceDaoImplTest {

  @Autowired
  private ResourceDaoImpl resourceDaoImpl;

@Test
 public void testFindResourceById() {
  ResourceComplex resource = resourceDaoImpl.findResourceById(1);
  
  Assert.assertEquals(resource.getIdResource(), 1);
  Assert.assertEquals(resource.getUser(), "user");
  Assert.assertEquals(resource.getCategory(), "category");
  Assert.assertEquals(resource.getTitle(), "title");
  Assert.assertEquals(resource.getType(), "type");
  Assert.assertEquals(resource.getPhoto(), null);
  Assert.assertEquals(resource.getShortDescription(), "Description!");
  Assert.assertEquals(resource.getTags(), "tags");
  Assert.assertEquals(resource.getRegisterDate(), "1999-01-08 04:05:06");
}

}
