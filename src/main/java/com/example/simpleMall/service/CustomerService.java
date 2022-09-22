package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
public interface CustomerService {

    Customer login(String loginName, String password);
}
