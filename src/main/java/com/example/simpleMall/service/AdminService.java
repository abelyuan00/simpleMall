package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Admin;

public interface AdminService {

    Admin login(String loginName, String password);
}
