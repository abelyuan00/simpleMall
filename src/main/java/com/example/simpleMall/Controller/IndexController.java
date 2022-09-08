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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

//    @PostMapping(value = "/loginCustomer")
//    public String loginCustomer(@RequestParam("loginName") String loginName,
//                        @RequestParam("password") String password,
//                        HttpSession session) {
//        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
//            session.setAttribute("errorMsg", "用户名或密码不能为空");
//            return "login";
//        }
//        Customer customer = AdminService.login(loginName, password);
//        if (admin != null) {
//            session.setAttribute("adminCode", admin.getAdminCode());
//            session.setAttribute("adminId", admin.getId());
//            //session过期时间设置为7200秒 即两小时
//            session.setMaxInactiveInterval(60 * 60 * 2);
//            return "redirect:/index";
//        } else {
//            session.setAttribute("errorMsg", "登录失败");
//            return "login";
//        }
//    }

    @PostMapping(value = "/loginAdmin")
    public String loginAdmin(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "login";
        }
        try {
            Admin admin = adminService.login(loginName, password);
            if (admin != null) {
                session.setAttribute("adminCode", admin.getAdminCode());
                session.setAttribute("adminId", admin.getId());
                //session过期时间设置为7200秒 即两小时
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

    @PostMapping(value = "/login")
    public String loginCustomer(@RequestParam("loginName") String loginName,
                             @RequestParam("password") String password,
                             HttpSession session) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "login";
        }
        try {
            Customer customer = customerService.login(loginName, password);
            if (customer != null) {
                session.setAttribute("customerId", customer.getId());
                //session过期时间设置为7200秒 即两小时
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
