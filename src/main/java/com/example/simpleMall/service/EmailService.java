package com.example.simpleMall.service;

import javax.mail.MessagingException;

public interface EmailService {

    Boolean sendEmail(String receiver, String subject, StringBuilder emailBody);
}
