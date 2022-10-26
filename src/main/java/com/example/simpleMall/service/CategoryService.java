package com.example.simpleMall.service;

import com.example.simpleMall.Entity.Category;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;

import java.util.List;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/10/2022, Monday
 **/
public interface CategoryService {

    /**
     * 查询后台管理系统分类分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getCategoryPage(PageQueryUtil pageUtil);

    String saveCategory(Category category);

    String updateCategory(Category category);

    Category getCategoryById(Long id);

    Boolean deleteCategoryById(Long id);
}
