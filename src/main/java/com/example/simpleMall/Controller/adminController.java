package com.example.simpleMall.Controller;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022
 **/

@Controller
public class adminController {


    @Resource
    AdminService adminService;

    @GetMapping({"/admin/profile"})
    public String profileAdmin() {
        return "admin/profileAdmin";
    }

    //Used in
    @GetMapping({"/admin/changePassword"})
    public String changePassword() {
        return "admin/changePassword";
    }

    //change customer information other than password
    @GetMapping({"/admin/customerManage"})
    public String customerManage() {
        return "admin/customerManage";
    }

    @GetMapping({"/admin/login"})
    public String loginAdmin() {
        return "admin/loginAdmin";
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

    @PostMapping(value = "/admin/changePassword")
    public String changePassword(@RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session) {

        if(null==session.getAttribute("adminId")){
            session.setAttribute("errorMsg","Please log in before change password");
            return "admin/changePassword";
        }
        String loginName = adminService.loadAdmin((Long) session.getAttribute("adminId")).getLoginName();
        Boolean result = adminService.updatePassword(loginName,originalPassword,newPassword);
        if (!result){
            session.setAttribute("errorMsg","Original Password did not match, please try again");
        }
        return "admin/changePassword";
    }
}

