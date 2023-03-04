package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Dao.UserLogin;
import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import com.example.simpleMall.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

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

        if (!isPasswordMatches)
            throw new RuntimeException("Password did not match, please try again");
        else if(userDao.findAdmin(loginName).getStatus().equals("locked"))
            throw new RuntimeException("User locked, please contact other admin");

        admin =  userLogin.loginAdmin(loginName);
        return admin;
    }

    @Override
    public Boolean updatePassword(String loginName, String passwordOld, String passwordNew) {

        Admin admin = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            admin = userDao.findAdmin(loginName);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        Boolean isPasswordMatches = encoder.matches(passwordOld,admin.getPassword());
        if(isPasswordMatches) {
            admin.encodePassword(passwordNew);
            userDao.updateAdmin(admin);
            return true;
        }
        else
            return false;

    }

    @Override
    public Boolean insertAdmin(String logInName, String password, String email, String nickname) {
        try{
            Admin newAdmin = new Admin();
            newAdmin.setLoginName(logInName);
            newAdmin.encodePassword(password);
            newAdmin.setEmail(email);
            newAdmin.setNickname(nickname);
            newAdmin.generateCode();
            userDao.insertAdmin(newAdmin);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Admin loadAdmin(Long id) {
        Admin admin = null;
        try {
            admin = userDao.findAdminById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public PageResult getPageListResult(PageQueryUtil pageUtil) {
        // data list for current page
        List<Customer> products = userDao.getCustomerList(pageUtil);
        // all data
        Integer total = userDao.getTotalCustomer(pageUtil);
        // page split
        PageResult pageResult = new PageResult(products, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
