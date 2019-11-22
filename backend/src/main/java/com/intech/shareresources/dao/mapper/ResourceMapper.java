package com.intech.shareresources.dao.mapper;

import java.sql.ResultSet;
import com.intech.shareresources.model.ResourceComplex;
import com.intech.shareresources.model.ResourceOfUser;

public interface ResourceMapper {

  ResourceComplex convertToResourceComplex(ResultSet rs);

  ResourceComplex convertToSingleResourceComplex(ResultSet rs);

  ResourceOfUser convertToResourceOfUser(ResultSet rs);
}
