package com.example.simpleMall.Controller;

import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.service.CustomerService;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Resource
    CustomerService customerService;

    @GetMapping({"/login"})
    public String loginCustomer() {
        return "customer/loginCustomer";
    }

    @GetMapping({"/profile"})
    public String profileCustomer() {
        return "customer/profileCustomer";
    }

    @GetMapping({"/register"})
    public String registerCustomer() {
        return "customer/registerCustomer";
    }

    @GetMapping({"/downloadFile"})
    public String downloadFile() {
        return "customer/downloadFile";
    }

    @GetMapping({"/changePassword"})
    public String changePassword() {
        return "customer/changePassword";
    }

    @GetMapping("/2048")
    public String game(){
        return "2048Game";
    }

    @PostMapping(value = "/login")
    public String loginCustomer(@RequestParam("loginName") String loginName,
                                @RequestParam("password") String password,
                                HttpSession session) {
        Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
        try {
            Customer customer = customerService.login(loginName, password);
            if (customer != null) {
                session.setAttribute("userId", customer.getId());
                session.setAttribute("role", customer.getRole());
                session.removeAttribute("errorMsgCustomer");
                //keep session alive for 7200 second
                session.setMaxInactiveInterval(60 * 60 * 2);
                String redirect = "/main";
                if(null!=session.getAttribute("redirectTo")) {
                    redirect = (String) session.getAttribute("redirectTo");
                    session.removeAttribute("redirectTo");
                }
                return "redirect:"+redirect;
            }
            else {
                session.setAttribute("errorMsgCustomer", "Can't find user, please check your login name or password");
                return "customer/loginCustomer";
            }
        }
        catch (Exception e){
            if(e.getCause() instanceof NullPointerException){
                session.setAttribute("errorMsgCustomer", "Can not find user with given combination");
            }
            else
                session.setAttribute("errorMsgCustomer", "Error occurred, Please contact admin");
            log.error(e.getMessage());
            return "customer/loginCustomer";
        }
    }



    @PostMapping(value = "/changePassword")
    public String changePassword(@RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session) {

        if(null==session.getAttribute("userId") || !"customer".equals(session.getAttribute("role"))){
            session.setAttribute("errorMsg","Please log in before change password");
            return "customer/changePassword";
        }
        String loginName = customerService.loadCustomer((Long) session.getAttribute("userId")).getLoginName();
        Boolean result = customerService.updatePassword(loginName,originalPassword,newPassword);
        if (result){
            session.setAttribute("successMsg","Password updated");
        }
        else
            session.setAttribute("errorMsg","Original Password did not match, please try again");
        return "customer/changePassword";
    }

    @GetMapping("/getSubInfo")
    public synchronized String getSubInfo(HttpSession session){
        if(null==session.getAttribute("userId") || !"customer".equals(session.getAttribute("role"))){
            session.setAttribute("errorMsg","Please log in");
            return "customer/loginCustomer";
        }
        return customerService.getSubInfo(session);
    }

    @PostMapping(value = "/register/submitInfo")
    public String saveRegisterInfo(@RequestParam("loginName") String loginName,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                    HttpSession session) throws Exception {

        try {
            Boolean result = customerService.registerCustomer(loginName,password,email);
            if(result) {
                session.setAttribute("successMsg", "Successfully registered");
            }
            else
                session.setAttribute("errorMsg","Registration failed please try again ");
            return "/customer/loginCustomer";
        }
        catch(Exception e) {
            LOGGER.error("Error occurred while processing request", e.getMessage());
            session.setAttribute("errorMsg", "Error occurred, Please try again");
            throw e;
        }
    }

}
