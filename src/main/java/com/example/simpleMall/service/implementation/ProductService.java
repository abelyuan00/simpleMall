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
        List<Product> products = productDao.findProducts(pageUtil);
        int total = productDao.getTotalProducts(pageUtil);
        PageResult pageResult = new PageResult(products, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
