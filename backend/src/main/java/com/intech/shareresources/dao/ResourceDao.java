package com.intech.shareresources.dao;

import java.util.List;
import com.intech.shareresources.model.ResourceComplex;
import com.intech.shareresources.model.ResourceOfUser;
import com.intech.shareresources.model.ResourcePicture;
import com.intech.shareresources.model.ResourceToEdit;
import com.intech.shareresources.model.ResourceToInsert;

public interface ResourceDao {
  boolean addNewResource(ResourceToInsert resource);

  List<ResourceComplex> findAll(String title, Long id);

  ResourceComplex findResourceById(long id);

  boolean editResource(ResourceToEdit resource);

  List<ResourceOfUser> findAllUserResources(int id);

  boolean changePicture(ResourcePicture resource);
}
