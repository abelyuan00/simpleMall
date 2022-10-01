package com.example.simpleMall.Controller;

import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.PageResult;
import com.example.simpleMall.Util.Result;
import com.example.simpleMall.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/2/2022, Sunday
 **/
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //TODO
    //
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        Result result = new Result();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            // 返回错误码
            result.setResultCode(500);
            // 错误信息
            result.setMessage("ERROR");
            return result;
        }
        // 封装查询参数
        PageQueryUtil queryParamList = new PageQueryUtil(params);
        // 查询并封装分页结果集
        PageResult productPage = productService.getProductPage(queryParamList);
        // 返回成功码
        result.setResultCode(200);
        result.setMessage("Data retrieve success ");
        // 返回分页数据
        result.setData(productPage);


        return result;
    }
}
