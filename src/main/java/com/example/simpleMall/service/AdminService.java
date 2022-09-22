package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Admin;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
public interface AdminService {

    Admin login(String loginName, String password);

    void changeCustomerPassword(String logInName, String oldPass, String newPass);

}
