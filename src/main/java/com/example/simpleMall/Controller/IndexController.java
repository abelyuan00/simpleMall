package com.example.simpleMall.Controller;


import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class IndexController {

    @Resource
    UserDao userDao;

    @GetMapping({"/login"})
    public String login(){
        return "login";
    }
    @GetMapping("/insert")
    public Boolean insert(String name, String password) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setCode(customer.getCode());
        customer.setPasswd(password);
        customer.setCreatedTime(new Date());
        customer.setUpdateTime(customer.getCreatedTime());
        userDao.insertCustomer(customer);
        return true;
    }





}
