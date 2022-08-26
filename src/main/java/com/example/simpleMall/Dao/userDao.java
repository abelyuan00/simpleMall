package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userDao {

    List<User> findAllUsers();

    int insertUser(User customer);

    int updateUser(User customer);

    int removeUser(Integer id);
}
