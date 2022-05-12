package com.sangeng.controller;

import com.sangeng.domin.result.ResponseResult;
import com.sangeng.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "文章分类管理")
@RestController
@RequestMapping("/category")
public class CategoryController  {

    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "查询文章分类列表")
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }

}
