package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Controller.CustomerController;
import com.example.simpleMall.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    public Boolean sendEmail(String receiver, String subject, StringBuilder emailBody) {
        String emailFrom = "";
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailFrom);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(String.valueOf(emailBody));
            emailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return false;
        }
    }

}
