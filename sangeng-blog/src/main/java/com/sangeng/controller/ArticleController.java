package com.sangeng.controller;


import com.sangeng.domin.entity.Article;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "分页查询文章信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum",required = false,value = "当前页"),
            @ApiImplicitParam(name = "pageSize",required = false,value = "每页大小"),
            @ApiImplicitParam(name = "categoryId",required = false,value = "分类id")})
    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                      @RequestParam(value = "categoryId",required = false) Long categoryId) {
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @ApiOperation(value = "根据文章表id查询文章详情信息")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "id",required = true,value = "文章id")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

}
