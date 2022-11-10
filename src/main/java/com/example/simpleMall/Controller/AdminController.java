package com.example.simpleMall.Controller;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import com.example.simpleMall.Util.Result;
import com.example.simpleMall.service.AdminService;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022
 **/

@Controller

public class AdminController {


    @Resource
    AdminService adminService;

    @Autowired
    Environment environment;



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
        Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
        try {
            Admin admin = adminService.login(loginName, password);
            if (admin != null) {
                session.setAttribute("adminId", admin.getId());
                session.removeAttribute("errorMsg");
                String redirect = (String) session.getAttribute("redirectTo");
                String port = environment.getProperty("local.server.port");
                String [] redirects = redirect.split(port,2);
                redirect = redirects[1];

                if(null!=session.getAttribute("customerId")){
                    session.removeAttribute("customerId");
                }
                //keep session alive for 7200 second
                session.setMaxInactiveInterval(60 * 60 * 2);
                return "redirect:"+redirect;
            }
            else {

                session.setAttribute("errorMsg", "password do not match");
                return "redirect:/admin/login";
            }
        }
        catch (RuntimeException e){
            if(e.getCause() instanceof NullPointerException){
                session.setAttribute("errorMsg", "Can not find user with given name");
            }
            else
                session.setAttribute("errorMsg", "Error occurred, Please check error message");

            log.error(e.getMessage());
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
        if (result){
            session.setAttribute("successMsg","Password updated");
        }
        else
            session.setAttribute("errorMsg","Original Password did not match, please try again");
        return "admin/changePassword";
    }


    @ResponseBody
    @RequestMapping(value = "/admin/customerList", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        Result result = new Result();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            result.setResultCode(500);
            result.setMessage("ERROR");
            return result;
        }
        // set in parameters
        PageQueryUtil queryParamList = new PageQueryUtil(params);
        PageResult productPage = adminService.getPageListResult(queryParamList);
        // success code
        result.setResultCode(200);
        result.setMessage("Data retrieve success ");
        // page split
        result.setData(productPage);
        return result;
    }
}

