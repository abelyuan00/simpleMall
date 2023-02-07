package com.example.simpleMall.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 8/15/2022, Thursday
 **/
@Controller
public class IndexController {

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

}
