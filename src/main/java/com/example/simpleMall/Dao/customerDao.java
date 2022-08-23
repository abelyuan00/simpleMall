package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Customer;

import java.util.List;

public interface customerDao {

    List<Customer> findAllUsers();

    int insertUser(Customer customer);

    int updateUser(Customer customer);

    int removeUser(Integer id);
}
