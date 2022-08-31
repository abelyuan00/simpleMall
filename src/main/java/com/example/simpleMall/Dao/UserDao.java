package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {

    void insertCustomer(Customer customer);

    void insertAdmin(Admin admin);

    void updateCustomer(Customer customer);

    void updateAdmin(Admin admin);

    void removeCustomer(Integer id);

    void removeAdmin(Integer id);

    void login(@Param("loginName")String loginName,@Param("password")String password);


}
