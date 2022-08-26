package com.example.simpleMall.Controller;


import com.example.simpleMall.Dao.userDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class IndexController {
//
//    @Resource
//    userDao UserDao;
    @GetMapping({"/login"})
    public String login(){
        return "login";
    }


}
