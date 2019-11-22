package com.intech.shareresources.service.impl;

import static org.mockito.Matchers.anyString;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.intech.shareresources.dao.ResourceDao;
import com.intech.shareresources.dto.ResourceOfUserDto;
import com.intech.shareresources.dto.ResourceToEditDto;
import com.intech.shareresources.dto.ResourceToInsertDto;
import com.intech.shareresources.model.ResourceOfUser;
import com.intech.shareresources.service.PhotoService;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceImplTest {


  @Mock
  private ResourceDao resourceDao;

  @Mock
  private PhotoService photoService;

  @InjectMocks
  private ResourceServiceImpl resourceServiceImpl = new ResourceServiceImpl();



   @Test
   public void testEditResource() throws FileNotFoundException, IOException {
   long idResource=1;
   long idCategory=1;
   Optional<String> type = Optional.of("type");
   Optional<String> shortDescription = Optional.of("description");
  
   Mockito.when(resourceDao.editResource(Mockito.any())).thenReturn(true);
   
   ResourceToEditDto resourceToEditDto = new ResourceToEditDto(idResource, "title", type,
   shortDescription, idCategory, new String[] {"tech"});
   
   Assert.assertTrue(resourceServiceImpl.editResource(resourceToEditDto));
   
   }

  @Test
  public void testAddResourceWithExitingPhoto() throws FileNotFoundException, IOException {
    long idUser = 1;
    Optional<String> type = Optional.of("type");
    Optional<String> shortDescription = Optional.of("description");
    long idCategory = 1;

    Mockito.when(resourceDao.addNewResource(Mockito.any())).thenReturn(true);
    
    ResourceToInsertDto resourceToInsertDto = new ResourceToInsertDto("titlu", idUser, type,
        shortDescription, idCategory, new String[] {"tech"}, Optional.of(new byte[] {1, 2, 3}));
    Assert.assertTrue(resourceServiceImpl.addResource(resourceToInsertDto));
  }
  
  @Test
  public void testAddResourceWithEmptyPhoto() throws FileNotFoundException, IOException {
    long idUser = 1;
    Optional<String> type = Optional.of("type");
    Optional<String> shortDescription = Optional.of("description");
    long idCategory = 1;

    Mockito.when(photoService.getBookPhoto(anyString())).thenReturn(new byte[] {1, 2, 3});
    Mockito.when(resourceDao.addNewResource(Mockito.any())).thenReturn(true);
    
    ResourceToInsertDto resourceToInsertDto = new ResourceToInsertDto("titlu", idUser, type,
        shortDescription, idCategory, new String[] {"tech"}, Optional.empty());
    Assert.assertTrue(resourceServiceImpl.addResource(resourceToInsertDto));
  }

  //TODO delte after refector code in class ResourceServiceImpl (setPhoto)
  @Test
  public void testGetAllResourceWitElementsWithEmptyPhoto() {
    long id = 1;
    Optional<String> type = Optional.of("type");
    Optional<String> shortDescription = Optional.of("description");
    long idCategory = 2;

    byte[] photo = new byte[] {1, 2, 3};

    ResourceOfUser resourceOfUser = new ResourceOfUser(id, "title", type, shortDescription,
        "category", idCategory, new String[] {"tech"}, Optional.empty());

    ResourceOfUserDto resourceOfUserDto = new ResourceOfUserDto();
    resourceOfUserDto.setIdCategory(2);
    resourceOfUserDto.setIdResource(1);
    resourceOfUserDto.setTitle("title");
    resourceOfUserDto.setType(type);
    resourceOfUserDto.setShortDescription(shortDescription);
    resourceOfUserDto.setCategoryName("category");
    resourceOfUserDto.setTags(new String[] {"tech"});
    resourceOfUserDto.setPhoto( Optional.of(photo));


    Mockito.when(resourceDao.findAllUserResources(2)).thenReturn(Arrays.asList(resourceOfUser));
    Mockito.when(photoService.getBookPhoto(anyString())).thenReturn(photo);
    List<ResourceOfUserDto> resource = resourceServiceImpl.getAllResourceOfUser(2);
    Assert.assertEquals(resourceOfUserDto, resource.get(0));

  }
  
  @Test
  public void testGetAllResourceWitElementsPhotoNotEmpty() {
    long id = 1;
    Optional<String> type = Optional.of("type");
    Optional<String> shortDescription = Optional.of("description");
    long idCategory = 2;

    byte[] photo = new byte[] {1, 2, 3};

    ResourceOfUser resourceOfUser = new ResourceOfUser(id, "title", type, shortDescription,
        "category", idCategory, new String[] {"tech"},  Optional.of(photo));

    ResourceOfUserDto resourceOfUserDto = new ResourceOfUserDto();
    resourceOfUserDto.setIdCategory(2);
    resourceOfUserDto.setIdResource(1);
    resourceOfUserDto.setTitle("title");
    resourceOfUserDto.setType(type);
    resourceOfUserDto.setShortDescription(shortDescription);
    resourceOfUserDto.setCategoryName("category");
    resourceOfUserDto.setTags(new String[] {"tech"});
    resourceOfUserDto.setPhoto( Optional.of(photo));


    Mockito.when(resourceDao.findAllUserResources(2)).thenReturn(Arrays.asList(resourceOfUser));
    List<ResourceOfUserDto> resource = resourceServiceImpl.getAllResourceOfUser(2);
    Assert.assertEquals(resourceOfUserDto, resource.get(0));

  }

  @Test
  public void testGetAllResourceEmpty() {
   
    Mockito.when(resourceDao.findAllUserResources(1)).thenReturn(Collections.emptyList());
    List<ResourceOfUserDto> resource = resourceServiceImpl.getAllResourceOfUser(1);
    Assert.assertTrue(resource.isEmpty());
  }
}
