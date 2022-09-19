package com.example.simpleMall.Controller;

import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class customerController {

    @Resource
    CustomerService customerService;

    @GetMapping({"/customer/login"})
    public String loginCustomer() {
        return "customer/loginCustomer";
    }

    @GetMapping({"/customer/profile"})
    public String profileCustomer() {
        return "customer/profileCustomer";
    }


    @PostMapping(value = "/customer/login")
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
                return "customer/loginCustomer";
            }
        }
        catch (Exception e){
            session.setAttribute("errorMsg", "Can not find user with given login name");
            return "customer/loginCustomer";
        }
    }

    @GetMapping({"/customer/changePassword"})
    public String changePassword() {
        return "customer/changePassword";
    }

    @PostMapping(value = "/customer/changePassword")
    public String changePassword(@RequestParam("loginName") String loginName,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session) {

        return "customer/changePassword";
    }
}
