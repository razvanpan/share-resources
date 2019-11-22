package com.intech.shareresources.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsFeed {

  private long idNewsFeed;
  private byte[] photo;
  private String email;
  private String title;
  private String category;
  private String fullName;

}
