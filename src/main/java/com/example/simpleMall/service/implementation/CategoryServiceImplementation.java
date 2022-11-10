package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.CategoryDao;
import com.example.simpleMall.Entity.Category;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import com.example.simpleMall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/16/2022, Sunday
 **/
@Service
public class CategoryServiceImplementation implements CategoryService {
    
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageResult getCategoryPage(PageQueryUtil pageUtil) {
        List<Category> categories = categoryDao.findCategoryList(pageUtil);
        Integer total = categoryDao.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCategory(Category category) {
        Category temp = categoryDao.selectByLevelAndName(category.getCategoryLevel(), category.getName());
        if (temp != null) {
            return "SUCCESS";
        }
        if (categoryDao.insertCategory(category) > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @Override
    public String updateCategory(Category category) {
        Category temp = categoryDao.findCategoryById(category.getId());
        if (temp == null) {
            return "DATA_NOT_EXIST";
        }
        Category temp2 = categoryDao.selectByLevelAndName(category.getCategoryLevel(), category.getName());
        if (temp2 != null && !temp2.getId().equals(category.getId())) {
            //同名且不同id 不能继续修改
            return "SAME_CATEGORY_EXIST";
        }
        category.setUpdateTime(new Date());
        if (categoryDao.updateCategory(category) > 0) {
            return "SUCCESS";
        }
        return "DB_ERROR";
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDao.findCategoryById(id);
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        //删除分类数据
        return categoryDao.deleteCategoryById(id) > 0;
    }


}
