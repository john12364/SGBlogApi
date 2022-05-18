package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.Article;
import com.sangeng.domin.result.ResponseResult;

public interface ArticleService extends IService<Article> {
    //查询最热们文章
    ResponseResult hotArticleList();
    //分页查询文章列表
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    //根据文章id查询文章详情信息
    ResponseResult getArticleDetail(Long id);
    //根据id更新Redis当中的浏览次数
    ResponseResult updateViewCount(Long id);
}
