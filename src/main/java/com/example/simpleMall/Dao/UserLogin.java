package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import org.apache.ibatis.annotations.Param;

public interface UserLogin {

    Admin loginAdmin(@Param("loginName")String loginName);

    Customer loginCustomer(@Param("loginName")String loginName);

}
