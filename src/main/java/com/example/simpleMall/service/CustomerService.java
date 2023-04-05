package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Customer;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
public interface CustomerService {

    Customer login(String loginName, String password);

    Customer loadCustomer(Long customerId);

    Boolean updatePassword(String loginName, String passwordOld, String passwordNew);

    Boolean registerCustomer(@RequestParam String loginName, @RequestParam String password,@RequestParam String email) throws Exception;

    String getSubInfo(HttpSession session);

    Boolean resetPassword(String loginName, String email, String nickname);
}
