package com.intech.shareresources.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.intech.shareresources.controller.ResourceController;
import com.intech.shareresources.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

  private static final Logger LOG = LogManager.getLogger(ResourceController.class);

  @Override
  public byte[] getBookPhoto(String bookTitle) {
    byte[] photo = null;
    try {
      int avatarNr = (bookTitle.length() * 94) % 300;
      photo = IOUtils.toByteArray(new URL("https://api.adorable.io/avatars/" + avatarNr));
    } catch (MalformedURLException e) {
      LOG.error(() -> "Error getting the photo fot her book with title: " + bookTitle, e);
    } catch (IOException e) {
      LOG.error(() -> "Error getting the photo fot her book with title: " + bookTitle, e);
    }
    return photo;
  }

  @Override
  public byte[] getUserAvatar() {
    Random random = new Random();
    int c = random.nextInt(100);
    byte[] bytes = null;
    try {
      int avatarNr = (c * 94) % 300;
      bytes = IOUtils.toByteArray(new URL("https://api.adorable.io/avatars/" + avatarNr));
    } catch (MalformedURLException e) {
      LOG.error(() -> "Error getting the photo for user ", e);
    } catch (IOException e) {
      LOG.error(() -> "Error getting the photo for user  ", e);
    }
    return bytes;
  }

}
