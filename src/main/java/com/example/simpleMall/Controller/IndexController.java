package com.example.simpleMall.Controller;


import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Entity.User;
import com.example.simpleMall.service.AdminService;
import com.example.simpleMall.service.CustomerService;
import com.example.simpleMall.service.implementation.AdminServiceImplementation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Locale;

@Controller
public class IndexController {


    @Resource
    AdminService adminService;

    @Resource
    CustomerService customerService;

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @GetMapping({"/admin/login"})
    public String loginAdmin() {
        return "loginAdmin";
    }

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }


    @PostMapping(value = "/admin/login")
    public String loginAdmin(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        HttpSession session) {
        try {
            Admin admin = adminService.login(loginName, password);
            if (admin != null) {
                session.setAttribute("adminId", admin.getId());
                //keep session alive for 7200 second
                session.setMaxInactiveInterval(60 * 60 * 2);
                return "redirect:/index";
            }
            else {
                session.setAttribute("errorMsg", "password do not match");
                return "loginAdmin";
            }
        }
        catch (Exception e){
            session.setAttribute("errorMsg", "Can not find user with given login name");
            return "loginAdmin";
        }
    }

    @PostMapping(value = "/login")
    public String loginCustomer(@RequestParam("loginName") String loginName,
                             @RequestParam("password") String password,
                             HttpSession session) {
        try {
            Customer customer = customerService.login(loginName, password);
            if (customer != null) {
                session.setAttribute("customerId", customer.getId());

                //keep session alive for 7200 second
                session.setMaxInactiveInterval(60 * 60 * 2);
                return "redirect:/index";
            }
            else {
                session.setAttribute("errorMsg", "password do not match");
                return "login";
            }
        }
        catch (Exception e){
            session.setAttribute("errorMsg", "Can not find user with given login name");
            return "login";
        }
    }
}
