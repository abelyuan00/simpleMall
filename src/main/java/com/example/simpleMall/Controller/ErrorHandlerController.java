package com.example.simpleMall.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@Controller
public class ErrorHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error/500";
    }
}
