package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
public interface UserLogin {

    Admin loginAdmin(@Param("loginName")String loginName);

    Customer loginCustomer(@Param("loginName")String loginName);

}
