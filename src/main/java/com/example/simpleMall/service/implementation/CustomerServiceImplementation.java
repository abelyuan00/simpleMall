package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
//import com.example.simpleMall.Entity.CustomerResources;
import com.example.simpleMall.Entity.CustomerResources;
import com.example.simpleMall.service.CustomerService;
import com.example.simpleMall.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    @Resource
    EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

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
    public Customer loadCustomer(Long userId) {
        Customer customer = null;
        try {
            customer = userDao.findCustomerById(userId);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
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
            LOGGER.error(e.getMessage());
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

    @Override
    public Boolean registerCustomer(String loginName, String password, String email) {
        try{
            Customer customer = new Customer();
            customer.setLoginName(loginName);
            customer.encodePassword(password);
            customer.generateCode();
            customer.setEmail(email);
            userDao.insertCustomer(customer);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();

            throw e;
        }
    }

    @Override
    public String getSubInfo(HttpSession session) {
        Long customerId = Long.valueOf((String) session.getAttribute("customerId"));
        Customer customer = userDao.findCustomerById(customerId);
        if("locked".equals(customer.getStatus())){
            session.setAttribute("accountError","Your bandwidth quota is used up, please contact admin");
            return "customer/downloadFile";
        }
        CustomerResources customerResources = userDao.getCustomerResources(customerId);
        return "customer/downloadFile";
    }

    @Override
    public Boolean resetPassword(String loginName, String email, String nickname) {

        try {
            Customer customer = userDao.findCustomer(loginName);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return false;
        }
        String subject = "Account Password Reset";
        StringBuilder emailBody = new StringBuilder();
        Boolean result = emailService.sendEmail(email, subject,emailBody);
        return true;
    }
}
