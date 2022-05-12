package com.sangeng.controller;


import com.sangeng.domin.entity.Article;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @ApiOperation(value = "文章信息")
    @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }

    @ApiOperation(value = "热门文章信息")
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

}
