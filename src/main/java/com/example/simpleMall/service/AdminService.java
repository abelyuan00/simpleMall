package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
public interface AdminService {

    Admin login(String loginName, String password);

    Boolean updatePassword(String logInName, String oldPass, String newPass);

    Boolean insertAdmin(String logInName, String password);

    Admin loadAdmin(Long adminId);

    PageResult getPageListResult(PageQueryUtil pageUtil);
}
