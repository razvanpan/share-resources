package com.intech.shareresources.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class PhotoServiceImplTest {

  private PhotoServiceImpl photoService = new PhotoServiceImpl();

  @Test
  public void testGetBookPhotoNotNull() throws FileNotFoundException, IOException {

    byte[] photo = photoService.getBookPhoto("Thinking in Java");

    // IOUtils.write(photo, new FileOutputStream("test.png"));

    Assert.assertNotNull(photo);
  }

  @Test
  public void testGetBookPhotoSameForTitle() throws FileNotFoundException, IOException {
    byte[] targetPhoto = IOUtils.toByteArray(this.getClass().getResourceAsStream("/thinking.png"));

    byte[] photo = photoService.getBookPhoto("thinking");
    Assert.assertArrayEquals(targetPhoto, photo);
  }

  @Test
  public void testGetUserPhoto() throws FileNotFoundException, IOException {
    byte[] photo = photoService.getUserAvatar();
    // IOUtils.write(photo, new FileOutputStream("test.png"));
    Assert.assertNotNull(photo);
  }
}
