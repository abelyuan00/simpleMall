package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.CategoryDao;
import com.example.simpleMall.Entity.Category;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import com.example.simpleMall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/16/2022, Sunday
 **/
public class CategoryServiceImplementation implements CategoryService {
    
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageResult getCategoryPage(PageQueryUtil pageUtil) {
        List<Category> categories = categoryDao.findCategoryList(pageUtil);
        Integer total = categoryDao.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(categories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }


}
