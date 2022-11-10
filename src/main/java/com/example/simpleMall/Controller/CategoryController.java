package com.example.simpleMall.Controller;

import com.example.simpleMall.Entity.Category;
import com.example.simpleMall.Util.PageQueryUtil;
import com.example.simpleMall.Util.Result;
import com.example.simpleMall.Util.ResultGen;
import com.example.simpleMall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 10/20/2022, Thursday
 **/
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Resource
    private CategoryService categoryService;



    @GetMapping("/categories")
    public String categoriesPage(HttpServletRequest request, @RequestParam("categoryLevel") Byte categoryLevel, @RequestParam("superiorId") Long superiorId, @RequestParam("previousId") Long previousId) {
//        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
//            return "error/500";
//        }
        request.setAttribute("path", "categories");
        request.setAttribute("superiorId", superiorId);
        request.setAttribute("previousId", previousId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "admin/categories";
    }
    
    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")) || StringUtils.isEmpty(params.get("categoryLevel")) || StringUtils.isEmpty(params.get("superiorId"))) {
            Result fail = new Result();
            fail.setMessage("Wrong param");
            fail.setResultCode(500);
            fail.setData(params);
            return fail;
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGen.genSuccessResult(categoryService.getCategoryPage(pageUtil));
    }

       /**
     * 添加
     */
    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Category category) {
        if (Objects.isNull(category.getCategoryLevel())
                || category.getName().isEmpty()
                || Objects.isNull(category.getSuperiorId())
                || Objects.isNull(category.getCategoryLevel())) {
            Result fail = new Result();
            fail.setMessage("Wrong param");
            fail.setResultCode(500);
            fail.setData(category);
            return fail;
        }
        String result = categoryService.saveCategory(category);
        if ("SUCCESS".equals(result)) {
            return ResultGen.genSuccessResult();
        } else {
            return ResultGen.genFailResult(result);
        }
    }


        /**
         * 修改
         */
        @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
        @ResponseBody
        public Result update(@RequestBody Category category) {
            if (Objects.isNull(category.getId())
                    || Objects.isNull(category.getCategoryLevel())
                    || StringUtils.isEmpty(category.getName())
                    || Objects.isNull(category.getSuperiorId())) {
                return ResultGen.genFailResult("参数异常！");
            }
            String result = categoryService.updateCategory(category);
            if ("SUCCESS".equals(result)) {
                return ResultGen.genSuccessResult();
            } else {
                return ResultGen.genFailResult(result);
            }
        }

        /**
         * 详情
         */
        @GetMapping("/categories/info/{id}")
        @ResponseBody
        public Result info(@PathVariable("id") Long id) {
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                return ResultGen.genFailResult("未查询到数据");
            }
            return ResultGen.genSuccessResult(category);
        }

        /**
         * 分类删除
         */
        @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
        @ResponseBody
        public Result delete(@RequestBody Long id) {

            if (categoryService.deleteCategoryById(id)) {
                return ResultGen.genSuccessResult();
            } else {
                return ResultGen.genFailResult("Deletion failed");
            }
        }


}
