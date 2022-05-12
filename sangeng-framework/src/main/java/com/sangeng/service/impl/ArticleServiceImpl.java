package com.sangeng.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domin.entity.Article;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.domin.vo.HotArticleVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//问题1：遇到爆红：经过检查发现是因为ArticleMapper没有extends BaseMapper<Article>
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<>(SystemConstants.ARTICLE_PAGE_CURRENT,SystemConstants.ARTICLE_PAGE_SIZE);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();

        //bean拷贝
//        for(Article article:articles) {
//            HotArticleVo articleVo = new HotArticleVo();
//            BeanUtils.copyProperties(article,articleVo);
//            articleVos.add(articleVo);
//        }

        //stream流
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles,HotArticleVo.class);
        return ResponseResult.okResult(articleVos);

    }
}
