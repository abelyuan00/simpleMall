package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;

public interface CustomerService {

    Customer login(String loginName, String password);
}
