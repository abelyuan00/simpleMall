package com.example.simpleMall.Controller;

import com.example.simpleMall.service.AdminService;
import com.example.simpleMall.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

@RestController
public class EmailController {
    @Autowired
    private JavaMailSender emailSender;

    @Resource
    EmailService emailService;

    @PostMapping(value = "/sendPassword")
    public String changePassword(@RequestParam("customerId") String customerId,
                                 HttpSession session) throws MessagingException {



        if(null==session.getAttribute("userId") || !"customer".equals(session.getAttribute("role"))){
            session.setAttribute("errorMsg","Please log in before change password");
            return "customer/changePassword";
        }
        if (result){
            session.setAttribute("successMsg","Password updated");
        }
        else
            session.setAttribute("errorMsg","Original Password did not match, please try again");
        return "customer/changePassword";
    }


}
