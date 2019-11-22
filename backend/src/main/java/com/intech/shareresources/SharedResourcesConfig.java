package com.intech.shareresources;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.intech.shareresources.test.config.SharedResourcesTestProperties;


@Configuration
@ComponentScan({"com.intech.shareresources"})
public class SharedResourcesConfig {
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

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new Jdk8Module());
    return mapper;
  }
}
