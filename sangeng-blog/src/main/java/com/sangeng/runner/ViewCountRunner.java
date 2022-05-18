package com.sangeng.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.domin.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Value("${ArticleViewCount}")
    private String ArticleViewCount;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化"+ArticleViewCount);

        //查询博客信息 id viewCount
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(Article::getId,Article::getViewCount);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        Map<String, Integer> viewCountMap = articles.parallelStream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));

        //存入Redis  变成map集合存入
        redisCache.setCacheMap(ArticleViewCount,viewCountMap);

    }
}
