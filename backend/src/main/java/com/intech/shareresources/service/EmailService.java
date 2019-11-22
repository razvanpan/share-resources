package com.intech.shareresources.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
  public void send(SimpleMailMessage email);
}
