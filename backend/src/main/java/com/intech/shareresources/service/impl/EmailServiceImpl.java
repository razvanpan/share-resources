package com.intech.shareresources.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.intech.shareresources.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Async
  public void send(SimpleMailMessage email) {
    javaMailSender.send(email);
  }

}
