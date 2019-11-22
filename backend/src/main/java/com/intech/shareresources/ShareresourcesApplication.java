package com.intech.shareresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Shareresources application.
 */
@SpringBootApplication(scanBasePackages = {"com.intech.shareresources"})
public class ShareresourcesApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(ShareresourcesApplication.class, args);
  }
}
