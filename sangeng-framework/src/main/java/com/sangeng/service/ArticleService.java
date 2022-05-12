package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.Article;
import com.sangeng.domin.result.ResponseResult;

public interface ArticleService extends IService<Article> {
    //查询最热们文章
    ResponseResult hotArticleList();
}
