package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImplementation implements AdminService {

    @Resource
    private UserLogin userLogin;

    @Resource
    private UserDao userDao;
    @Override
    public Admin login(String loginName, String password) {
        Admin admin = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String storedPassword = (String) userDao.findAdmin(loginName).get("password");
        Boolean isPasswordMatches = encoder.matches(encoder.encode(password), storedPassword);
        if(isPasswordMatches) {
            admin =  userLogin.loginAdmin(loginName);
        }
        return admin;
    }
}
