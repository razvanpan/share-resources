package com.intech.shareresources.test.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;	

@Configuration
@ComponentScan({"com.intech.shareresources.test.config","com.intech.shareresources.dao","com.intech.shareresources.util"})
@EnableAutoConfiguration
public class SharedResourcesTestConfig {

	@Autowired
	private SharedResourcesTestProperties prop;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(prop.getDriverClassName());
		dataSource.setUrl(prop.getUrl());
		dataSource.setUsername(prop.getUsername());
		dataSource.setPassword(prop.getPassword());
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
}
