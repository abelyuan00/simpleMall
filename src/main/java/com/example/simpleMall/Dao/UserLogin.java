package com.example.simpleMall.Dao;

import org.apache.ibatis.annotations.Param;

public interface UserLogin {

    void loginAdmin(@Param("loginName")String loginName, @Param("password")String password);

}
