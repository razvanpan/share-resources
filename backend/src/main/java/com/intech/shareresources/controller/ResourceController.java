package com.intech.shareresources.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.intech.shareresources.dto.ResourceDetailedDto;
import com.intech.shareresources.dto.ResourceOfUserDto;
import com.intech.shareresources.dto.ResourcePictureDto;
import com.intech.shareresources.dto.ResourceToBrowseDto;
import com.intech.shareresources.dto.ResourceToEditDto;
import com.intech.shareresources.dto.ResourceToInsertDto;
import com.intech.shareresources.service.ResourceService;

@RestController()
@RequestMapping(value = "/api")
@ControllerAdvice
public class ResourceController {

  private static final Logger LOG = LogManager.getLogger(ResourceController.class);

  @Autowired
  private ResourceService resourceService;

  @RequestMapping(value = "user/{id}/resource", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public boolean insertResource(@PathVariable("id") int id,
      @Valid @RequestBody ResourceToInsertDto resourceDto) {
    LOG.info(() -> "Creating new resource...");
    resourceDto.setIdUser(id);
    boolean insert = resourceService.addResource(resourceDto);
    LOG.info(() -> "Resource created successfully!");
    return insert;
  }

  @RequestMapping(value = "/resource/{id}/picture", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
  public boolean changePicture(@PathVariable("id") int id,
      @Valid @RequestBody ResourcePictureDto resourceDto) {
    LOG.info(() -> "Updating new picture...");
    resourceDto.setIdResource(id);
    boolean edit = resourceService.changePicture(resourceDto);
    LOG.info(() -> "Resource picture updated successfully!");
    return edit;
  }


  @RequestMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  @ResponseBody
  public List<ResourceToBrowseDto> allResource(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "idcategory", required = false) Long id) {
    LOG.info(() -> "Getting resources content...");
    List<ResourceToBrowseDto> resources = resourceService.getAllResource(title, id);
    LOG.info(() -> "Resources retrieved successfully!");
    return resources;
  }

  @RequestMapping(value = "/user/{id}/resource", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public List<ResourceOfUserDto> allResourceOfUser(@PathVariable("id") int id) {
    LOG.info(() -> "Getting resources content...");
    List<ResourceOfUserDto> resources = resourceService.getAllResourceOfUser(id);
    LOG.info(() -> "Resources retrieved successfully!");
    return resources;
  }

  @RequestMapping(value = "/resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  @ResponseBody
  public ResourceDetailedDto resourceById(@PathVariable("id") int id) {
    LOG.info(() -> "Commencing search...");
    ResourceDetailedDto resourceById = resourceService.getResourceById(id);
    LOG.info(() -> "Resource retrieved!");
    return resourceById;
  }


  @RequestMapping(value = "/resource/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
  public boolean editResource(@PathVariable("id") int id,
      @Valid @RequestBody ResourceToEditDto resourceDto) {
    LOG.info(() -> "Updating new resource...");
    resourceDto.setIdResource(id);
    boolean edit = resourceService.editResource(resourceDto);
    LOG.info(() -> "Resource updated successfully!");
    return edit;
  }


}
