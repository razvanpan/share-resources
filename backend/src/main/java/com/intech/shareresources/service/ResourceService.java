package com.intech.shareresources.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dto.ResourceDetailedDto;
import com.intech.shareresources.dto.ResourceOfUserDto;
import com.intech.shareresources.dto.ResourcePictureDto;
import com.intech.shareresources.dto.ResourceToBrowseDto;
import com.intech.shareresources.dto.ResourceToEditDto;
import com.intech.shareresources.dto.ResourceToInsertDto;

@Service
public interface ResourceService {
  boolean addResource(ResourceToInsertDto resourceDto);

  List<ResourceToBrowseDto> getAllResource(String title, Long id);

  ResourceDetailedDto getResourceById(int id);

  boolean editResource(ResourceToEditDto resourceDto);

  List<ResourceOfUserDto> getAllResourceOfUser(int id);

  boolean changePicture(ResourcePictureDto resourceDto);

}
