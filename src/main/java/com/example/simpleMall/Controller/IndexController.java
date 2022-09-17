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


    @Resource
    AdminService adminService;

    @Resource
    CustomerService customerService;

    @GetMapping({"/login"})
    public String login() {
        return "customer/login";
    }

    @GetMapping({"/customer/profile"})
    public String profileCustomer() {
        return "customer/profileCustomer";
    }

    @GetMapping({"/admin/profile"})
    public String profileAdmin() {
        return "customer/profileAdmin";
    }

    @GetMapping({"/admin/changePassword"})
    public String changePassword() {
        return "admin/changePassword";
    }

    @GetMapping({"/admin/login"})
    public String loginAdmin() {
        return "admin/loginAdmin";
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
                return "redirect:/admin/login";
            }
        }
        catch (Exception e){
            session.setAttribute("errorMsg", "Can not find user with given login name");
            return "redirect:/admin/login";
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
