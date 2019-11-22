package com.intech.shareresources.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.ResourceDao;
import com.intech.shareresources.dto.ResourceDetailedDto;
import com.intech.shareresources.dto.ResourceOfUserDto;
import com.intech.shareresources.dto.ResourcePictureDto;
import com.intech.shareresources.dto.ResourceToBrowseDto;
import com.intech.shareresources.dto.ResourceToEditDto;
import com.intech.shareresources.dto.ResourceToInsertDto;
import com.intech.shareresources.dto.UserDetailDto;
import com.intech.shareresources.model.ResourceComplex;
import com.intech.shareresources.model.ResourceOfUser;
import com.intech.shareresources.model.ResourcePicture;
import com.intech.shareresources.model.ResourceToEdit;
import com.intech.shareresources.model.ResourceToInsert;
import com.intech.shareresources.model.UserBase;
import com.intech.shareresources.service.PhotoService;
import com.intech.shareresources.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

  private static final Logger LOG = LogManager.getLogger(ResourceServiceImpl.class);
  @Autowired
  private ResourceDao resourceDao;
  @Autowired
  private PhotoService photoService;

  @Override
  public boolean editResource(ResourceToEditDto resourceDto) {
    LOG.info(() -> "Converting to ResourceToEdit ");
    ResourceToEdit res = new ResourceToEdit();
    res.setIdResource(resourceDto.getIdResource());
    res.setTitle(resourceDto.getTitle());
    res.setType(resourceDto.getType());
    res.setShortDescription(resourceDto.getShortDescription());
    res.setIdCategory(resourceDto.getIdCategory());
    res.setTags(resourceDto.getTags());
    return resourceDao.editResource(res);
  }

  public List<ResourceOfUserDto> getAllResourceOfUser(int id) {
    LOG.info(() -> "Looking for user's resources resources in the database...");
    List<ResourceOfUser> resources = resourceDao.findAllUserResources(id);
    if (resources.isEmpty()) {
      LOG.error(() -> "No table content");
    }
    LOG.info(() -> "Mapping between object-List<ResourceDto>");
    List<ResourceOfUserDto> resourceDtos =
        resources.stream().map(this::convertToResourceUserDto).collect(Collectors.toList());
    LOG.info(() -> "The map has been made");
    return resourceDtos;

  }

  @Override
  public boolean addResource(ResourceToInsertDto resourceDto) {
    LOG.info(() -> "Adding resource ");
    LOG.debug(() -> "Adding resource in Service implements");

    ResourceToInsert res = new ResourceToInsert();
    res.setTitle(resourceDto.getTitle());
    res.setIdUser(resourceDto.getIdUser());
    res.setType(resourceDto.getType());
    res.setShortDescription(resourceDto.getShortDescription());
    res.setIdCategory(resourceDto.getIdCategory());
    res.setTags(resourceDto.getTags());
    res.setPhoto(Optional
        .of(resourceDto.getPhoto().orElse(photoService.getBookPhoto(resourceDto.getTitle()))));
    return resourceDao.addNewResource(res);
  }

  public ResourceOfUserDto convertToResourceUserDto(ResourceOfUser resource) {
    LOG.info(() -> "Converting a ResourceComplex to ResourceDetailDto");
    ResourceOfUserDto resourceOfUser = new ResourceOfUserDto();

    resourceOfUser.setIdResource(resource.getIdResource());
    resourceOfUser.setTitle(resource.getTitle());
    resourceOfUser.setType(resource.getType());
    resourceOfUser.setShortDescription(resource.getShortDescription());
    resourceOfUser.setCategoryName(resource.getCategoryName());
    resourceOfUser.setTags(resource.getTags());
    resourceOfUser.setIdCategory(resource.getIdCategory());
    //TODO refector to delete the set of photo, if de enty in the db has no photo
    resourceOfUser.setPhoto(Optional
        .of(resource.getPhoto().orElse(photoService.getBookPhoto(resourceOfUser.getTitle()))));
    LOG.info(() -> "ResourceDetailDto returned");
    return resourceOfUser;
  }


  @Override
  public List<ResourceToBrowseDto> getAllResource(String title, Long id) {
    LOG.info(() -> "Looking for all resources in the database...");
    List<ResourceComplex> resources = resourceDao.findAll(title, id);
    if (resources.isEmpty()) {
      LOG.error(() -> "No table content");
    }
    LOG.info(() -> "Mapping between object-List<ResourceDto>");
    List<ResourceToBrowseDto> resourceDtos =
        resources.stream().map(this::convertToResourceDto).collect(Collectors.toList());
    LOG.info(() -> "The map has been made");
    return resourceDtos;
  }

  @Override
  public ResourceDetailedDto getResourceById(int id) {
    LOG.info(() -> "Looking for the resource in the database...");
    ResourceComplex resourceById = resourceDao.findResourceById(id);
    LOG.info(() -> "Converting resource for usability...");
    ResourceDetailedDto resourceDto = convertToResourceDetailDto(resourceById);
    LOG.info(() -> "Retrieving resource...");
    return resourceDto;

  }

  private ResourceDetailedDto convertToResourceDetailDto(ResourceComplex resource) {
    LOG.info(() -> "Converting a ResourceComplex to ResourceDetailDto");
    ResourceDetailedDto resDetail = new ResourceDetailedDto();

    resDetail.setIdResource(resource.getIdResource());
    resDetail.setTitle(resource.getTitle());
    resDetail.setUserDetail(convertFromUser(resource.getUser()));
    resDetail.setType(resource.getType());
    resDetail.setShortDescription(resource.getShortDescription());
    resDetail.setCategoryName(resource.getCategory().getName());
    resDetail.setTags(resource.getTags());
    resDetail.setPhoto(
        Optional.of(resource.getPhoto().orElse(photoService.getBookPhoto(resDetail.getTitle()))));
    resDetail.setPhone(resource.getUser().getPhone());
    resDetail.setEmail(resource.getUser().getEmail());
    resDetail.setFullNameUser(resource.getUser().getFirstName() + " "+ resource.getUser().getLastName());
    LOG.info(() -> "ResourceDetailDto returned");
    return resDetail;
  }

  private UserDetailDto convertFromUser(UserBase u) {
    LOG.info(() -> "Converting a UserBase to UserDetailDto");
    UserDetailDto usrDetail = new UserDetailDto();
    usrDetail.setIdUser(u.getIdUser());
    usrDetail.setEmail(u.getEmail());
    usrDetail.setFirstName(u.getFirstName());
    usrDetail.setLastName(u.getLastName());
    usrDetail.setShortDescription(u.getShortDescription());
    usrDetail.setPhoto(u.getPhoto());
    usrDetail.setPhone(u.getPhone());
    LOG.info(() -> "UserDetailDto returned");
    return usrDetail;
  }

  private ResourceToBrowseDto convertToResourceDto(ResourceComplex resource) {
    LOG.info(() -> "Converting a ResourceComplex to ResourceBrowseDto");
    ResourceToBrowseDto resDto = new ResourceToBrowseDto();

    resDto.setIdResource(resource.getIdResource());
    resDto.setCategoryName(resource.getCategory().getName());
    resDto.setIdUser(resource.getUser().getIdUser());
    resDto.setFullNameUser(
        resource.getUser().getFirstName() + " " + resource.getUser().getLastName());
    resDto.setPhoto(resource.getPhoto());
    resDto.setShortDescription(resource.getShortDescription());
    resDto.setTags(resource.getTags());
    resDto.setTitle(resource.getTitle());
    resDto.setType(resource.getType());
    LOG.info(() -> "ResourceComplex returned");
    return resDto;
  }

  @Override
  public boolean changePicture(ResourcePictureDto resourceDto) {

    LOG.info(() -> "Converting a ResourcePictureDto to ResourcePicture");
    ResourcePicture resource = new ResourcePicture();
    resource.setIdResource(resourceDto.getIdResource());
    resource.setPhoto(resourceDto.getPhoto());
    return resourceDao.changePicture(resource);
  }

}
