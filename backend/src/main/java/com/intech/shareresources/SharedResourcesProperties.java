package com.intech.shareresources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertySource("classpath:/application.properties")
@ConfigurationProperties("datasource")
@Component
public class SharedResourcesProperties {
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
