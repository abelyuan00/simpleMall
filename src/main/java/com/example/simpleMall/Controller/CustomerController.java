package com.example.simpleMall.Controller;

import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import com.example.simpleMall.Util.Result;
import com.example.simpleMall.service.CustomerService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CustomerController {

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

    @GetMapping({"/customer/register"})
    public String registerCustomer() {
        return "customer/registerCustomer";
    }

    @PostMapping(value = "/customer/login")
    public String loginCustomer(@RequestParam("loginName") String loginName,
                                @RequestParam("password") String password,
                                HttpSession session) {
        Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
        try {
            if(null!=session.getAttribute("adminId")){
                session.setAttribute("errorMsg","Please log out admin account first");
                return "customer/loginCustomer";
            }
            Customer customer = customerService.login(loginName, password);
            if (customer != null) {
                session.setAttribute("customerId", customer.getId());
                session.removeAttribute("errorMsg");
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
            if(e.getCause() instanceof NullPointerException){
                session.setAttribute("errorMsg", "Can not find user with given name");
            }
            else
                session.setAttribute("errorMsg", "Error occurred, Please contact admin");
            log.error(e.getMessage());
            return "customer/loginCustomer";
        }
    }

    @GetMapping({"/customer/changePassword"})
    public String changePassword() {
        return "customer/changePassword";
    }

    @PostMapping(value = "/customer/changePassword")
    public String changePassword(@RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session) {

        if(null==session.getAttribute("customerId")){
            session.setAttribute("errorMsg","Please log in before change password");
            return "customer/changePassword";
        }
        String loginName = customerService.loadCustomer((Long) session.getAttribute("customerId")).getLoginName();
        Boolean result = customerService.updatePassword(loginName,originalPassword,newPassword);
        if (result){
            session.setAttribute("successMsg","Password updated");
        }
        else
            session.setAttribute("errorMsg","Original Password did not match, please try again");
        return "customer/changePassword";
    }

}
