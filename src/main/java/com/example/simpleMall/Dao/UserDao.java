package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.*;
import com.example.simpleMall.Util.PageQueryUtil;
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

    Admin findAdminById(@Param("userId")Long adminId);

    Customer findCustomer(@Param("loginName")String loginName);

    Customer findCustomerById(@Param("userId")Long customerId);

    List<Customer> getCustomerList(PageQueryUtil pageUtil);

    Integer getTotalCustomer(PageQueryUtil pageUtil);

    CustomerResources getCustomerResources(@Param("userId")Long customerId);





}
