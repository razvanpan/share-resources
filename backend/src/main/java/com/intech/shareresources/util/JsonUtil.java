package com.intech.shareresources.util;

import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class JsonUtil {

  private Gson gson;

  public JsonUtil() {
    gson = new Gson();
  }

  public String convertToJson(Object object) {
    return gson.toJson(object);
  }

  public <T> T convertFromJson(String json, Class<T> clazz) {
    return gson.fromJson(json, clazz);
  }
}
