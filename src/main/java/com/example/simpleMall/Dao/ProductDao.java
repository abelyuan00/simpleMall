package com.example.simpleMall.Dao;

import com.example.simpleMall.Entity.Product;
import com.example.simpleMall.Util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/28/2022, Wednesday
 **/
@Mapper
public interface ProductDao {

    /**
     * 返回分页数据列表
     *
     * @param pageUtil
     * @return
     */
    List<Product> findProducts(PageQueryUtil pageUtil);

    /**
     * 返回数据总数
     *
     * @param pageUtil
     * @return
     */
    int getTotalProducts(PageQueryUtil pageUtil);
}
