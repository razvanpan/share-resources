package com.intech.shareresources.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertySource("application-test.properties")
@ConfigurationProperties("datasource")
@Component
public class SharedResourcesTestProperties {
  /** The postgres driver. */
  private String postgresDriver;

  /** The url. */
  private String url;

  /** The host. */
  private String username;

  /** The passwd. */
  private String password;

  /** The db name. */
  private String driverClassName;

}
