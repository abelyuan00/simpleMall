package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@Service
public class AdminServiceImplementation implements AdminService {

    @Resource
    private UserLogin userLogin;

    @Resource
    private UserDao userDao;
    @Override
    public Admin login(String loginName, String password) throws RuntimeException{
        Admin admin = null;
        String storedPassword = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            storedPassword = userDao.findAdmin(loginName).getPassword();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        Boolean isPasswordMatches = encoder.matches(password,storedPassword);
        if(isPasswordMatches) {
            admin =  userLogin.loginAdmin(loginName);
        }
        return admin;
    }

    @Override
    public void changeCustomerPassword(String logInName, String oldPass, String newPass) {

        Customer customer = userDao.findCustomer(logInName);

    }

}
