package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Admin;
import com.example.simpleMall.Entity.Category;
import com.example.simpleMall.Entity.Customer;
import com.example.simpleMall.Util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/10/2022, Monday
 **/
@Mapper
public interface CategoryDao {

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);

    List<Category> findByLevelAndSuperiorIds( Integer categoryLevel,List<Long> superiorIds);

    List<Category> findCategoryList(PageQueryUtil pageUtil);

    Integer getTotalGoodsCategories(PageQueryUtil pageUtil);




}
