package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

public class AdminServiceImplementation implements AdminService {

    @Resource
    private UserLogin userLogin;
    @Override
    public void login(String loginName, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordMd5 = encoder.encode(password);
        userLogin.loginAdmin(loginName, passwordMd5);
    }
}
