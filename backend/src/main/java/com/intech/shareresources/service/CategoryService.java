package com.intech.shareresources.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dto.CategoryDto;

@Service
public interface CategoryService {

  List<CategoryDto> getAllCategories();

}
