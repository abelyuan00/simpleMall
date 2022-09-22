package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/

@Mapper
public interface UserDao {

    void insertCustomer(Customer customer);

    void insertAdmin(Admin admin);

    void updateCustomer(Customer customer);

    void updateAdmin(Admin admin);

    void removeCustomer(Integer id);

    void removeAdmin(Integer id);

    Admin findAdmin(@Param("loginName")String loginName);

    Admin findAdminById(@Param("Id")Integer adminId);

    Customer findCustomer(@Param("loginName")String loginName);

    Customer findCustomerById(@Param("Id")Integer customerId);





}
