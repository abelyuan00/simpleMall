package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.service.CustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@Service
public class CustomerServiceImplementation implements CustomerService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserLogin userLogin;

    @Override
    public Customer login(String loginName, String password) {
        Customer customer = null;
        String storedPassword = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            storedPassword = userDao.findCustomer(loginName).getPassword();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        Boolean isPasswordMatches = encoder.matches(password,storedPassword);
        if(isPasswordMatches) {
            customer =  userLogin.loginCustomer(loginName);
        }
        return customer;
    }

    @Override
    public Customer loadCustomer(Long id) {
        Customer customer = null;
        try {
            customer = userDao.findCustomerById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Boolean updatePassword(String loginName, String passwordOld, String passwordNew) {

        Customer customer = new Customer();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            customer = userDao.findCustomer(loginName);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        Boolean isPasswordMatches = encoder.matches(passwordOld,customer.getPassword());
        if(isPasswordMatches) {
            customer.encodePassword(passwordNew);
            userDao.updateCustomer(customer);
            return true;
        }
        else
            return false;
    }
}
