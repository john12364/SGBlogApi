package com.sangeng.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domin.entity.Article;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.domin.vo.Article.ArticleDetailVo;
import com.sangeng.domin.vo.Article.ArticleListVo;
import com.sangeng.domin.vo.Article.HotArticleVo;
import com.sangeng.domin.vo.PageVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//问题1：遇到爆红：经过检查发现是因为ArticleMapper没有extends BaseMapper<Article>
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;
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

    @Override
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序排列
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        List<Article> articles = page.getRecords();
        articles.parallelStream()
                .map(article ->
                    article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())
                ).collect(Collectors.toList());

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles,ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
        //articleId去查询articleName进行设置
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article,ArticleDetailVo.class);
        //根据分类id查询分类名
        categoryService.getById(articleDetailVo.getCategoryId());
        articleDetailVo.setCategoryName(articleDetailVo.getCategoryName());
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}
