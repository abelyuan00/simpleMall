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
            // error code
            result.setResultCode(500);
            result.setMessage("ERROR");
            return result;
        }
        PageQueryUtil queryParamList = new PageQueryUtil(params);
        // search data and split page
        PageResult productPage = productService.getProductPage(queryParamList);
        result.setResultCode(200);
        result.setMessage("Data retrieve success ");
        // page data
        result.setData(productPage);


        return result;
    }
}
