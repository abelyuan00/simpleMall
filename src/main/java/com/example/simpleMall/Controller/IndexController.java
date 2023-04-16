package com.example.simpleMall.Controller;


import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.service.AdminService;
import com.example.simpleMall.service.CustomerService;
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

    @GetMapping({"/main"})
    public String mainPage() {
        return "main-page";
    }

    @GetMapping("/index")
    public String index() {return "index"; }


    @GetMapping({"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/product")
    public String product() {
        return "product/productList";
    }

    @GetMapping("/chart")
    public String chart(){
        return "customer/chartjs";
    }

    @GetMapping("/shopMain")
    public String shopMain(){
        return "shop/shop-main";
    }

    @GetMapping("/shop")
    public String goShopping(){
        return "shop/shop";
    }


    @GetMapping ("/getUserIconPath")
    @ResponseBody
    public Map<String, String> getUserIconPath(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String iconPath = "../../dist/img/boxed-bg.jpg";
        String userName = null;
        Admin admin = null;
        Customer customer = null;
        Long id = (Long)session.getAttribute("userId");
        if(null!=session.getAttribute("userId") && null!=session.getAttribute("role")) {
            if ("admin".equals(session.getAttribute("role"))) {
                admin = adminService.loadAdmin(id);
                iconPath = admin.getIconPath() == null? "/dist/img/minions.jpg" :admin.getIconPath() ;
                userName = admin.getNickname();
            } else if ("customer".equals(session.getAttribute("role"))){
                customer = customerService.loadCustomer(id);
                iconPath = customer.getIconPath() == null? "/dist/img/minions.jpg": customer.getIconPath();
                userName = customer.getName();
            }
        }

        response.put("iconPath", iconPath);
        response.put("userName",userName);
        return response;
    }
}
