package com.example.simpleMall.service.implementation;

import com.example.simpleMall.Dao.ProductDao;
import com.example.simpleMall.Dao.UserDao;
import com.example.simpleMall.Entity.Product;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/28/2022, Wednesday
 **/
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public PageResult getProductPage(PageQueryUtil pageUtil) {
        // 当前页码中的数据列表
        List<Product> products = productDao.findProducts(pageUtil);
        // 数据总条数，用于计算分页数据
        int total = productDao.getTotalProducts(pageUtil);
        // 分页信息封装
        PageResult pageResult = new PageResult(products, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
