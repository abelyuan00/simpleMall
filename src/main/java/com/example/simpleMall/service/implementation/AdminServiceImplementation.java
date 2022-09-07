package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminServiceImplementation implements AdminService {

    @Resource
    private UserLogin userLogin;

    @Resource
    private UserDao userDao;
    @Override
    public Admin login(String loginName, String password) {
        Admin admin = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String storedPassword =  userDao.findAdmin(loginName).getPassword();
        Boolean isPasswordMatches = encoder.matches(password,storedPassword);
        if(isPasswordMatches) {
            admin =  userLogin.loginAdmin(loginName);
        }
        return admin;
    }
}
