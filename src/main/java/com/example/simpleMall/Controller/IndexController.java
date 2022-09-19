package com.example.simpleMall.Controller;


import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.service.AdminService;
import com.example.simpleMall.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    @GetMapping({"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }


}
