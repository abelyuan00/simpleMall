package com.example.simpleMall.Config;

import com.example.simpleMall.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/21/2022, Thursday
 **/
@Configuration
public class LogInWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .addPathPatterns("/customer/**")
                .excludePathPatterns("/customer/register/**")
                .excludePathPatterns("/customer/login");
    }
}
