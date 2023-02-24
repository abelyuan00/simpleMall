package com.example.simpleMall.Controller;


import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Entity.User;
import com.example.simpleMall.service.AdminService;
import com.example.simpleMall.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 8/15/2022, Thursday
 **/
@Controller
public class IndexController {

    @Resource
    AdminService adminService;

    @Resource
    CustomerService customerService;

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }



    @GetMapping({"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/product")
    public String product() {
        return "product/productList";
    }

    @GetMapping("/chart")
    public String chart(){
        return "customer/chartjs";
    }

    @GetMapping ("/getUserIconPath")
    @ResponseBody
    public Map<String, String> getUserIconPath(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String iconPath = null;
        Admin admin = null;
        User user = null;
        Long id = (Long)session.getAttribute("adminId");
        if(null!=session.getAttribute("adminId")) {
            admin = adminService.loadAdmin(Long.valueOf(id));
            iconPath = admin.getIconPath();
        }

        else if (null==session.getAttribute("adminId")&&null!=session.getAttribute("customerId")) {
            user = customerService.loadCustomer(Long.valueOf((String) session.getAttribute("adminId")));
            iconPath = user.getIconPath();
        }
            response.put("iconPath", iconPath);
        return response;
    }
}
