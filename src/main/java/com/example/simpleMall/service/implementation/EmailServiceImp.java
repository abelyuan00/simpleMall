package com.example.simpleMall.service.implementation;

import com.example.simpleMall.service.EmailService;
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

    public Boolean sendEmail(String receiver, String subject, StringBuilder emailBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("from@example.com");
        helper.setTo("to@example.com");
        helper.setSubject("Test email");
        helper.setText(String.valueOf(emailBody));
        emailSender.send(message);
        return true;
    }

}
