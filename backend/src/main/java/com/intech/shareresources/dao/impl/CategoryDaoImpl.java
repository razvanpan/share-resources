package com.intech.shareresources.dao.impl;

import java.sql.ResultSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.intech.shareresources.dao.CategoryDao;
import com.intech.shareresources.dao.mapper.CategoryMapper;
import com.intech.shareresources.model.CategoryBase;

@Service
public class CategoryDaoImpl implements CategoryDao {

	private static final Logger LOG = LogManager.getLogger(CategoryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private CategoryMapper catMap;

	@Autowired
	public void setCatMap(CategoryMapper catMap) {
		this.catMap = catMap;
	}

	public CategoryBase findbyCategoryId(long id) {
		String sql = "SELECT * FROM category WHERE idcategory= ?";
		CategoryBase category = jdbcTemplate.query(sql, new Object[] { id },
				(ResultSet rs) -> catMap.convertToSingleCategoryBase(rs));
		LOG.info(() -> "Category with coresponding ID was found!");
		return category;
	}

	public List<CategoryBase> getAllCategories() {
		LOG.info(() -> "Bring all date from category table");
		String sql = "SELECT * FROM category";

		LOG.info(() -> "All data from table category has been selected");
		LOG.info(() -> "Convert all data from db into single object");
		List<CategoryBase> categories = jdbcTemplate.query(sql,
				(ResultSet rs, int rowNm) -> catMap.convertToCategoryBase(rs));
		LOG.info(() -> "The converson has been made");
		LOG.info(() -> "Resource returned");
		return categories;
	}

}
