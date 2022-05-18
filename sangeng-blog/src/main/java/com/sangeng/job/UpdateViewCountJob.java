package com.sangeng.job;

import com.sangeng.domin.entity.Article;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Value("${ArticleViewCount}")
    private String ArticleViewCount;

    @Scheduled(cron = "0/60 * * * * ?")
    public void updateViewCount(){

        //获取redis当中的数据
        Map<String,Integer> viewCountMap = redisCache.getCacheMap(ArticleViewCount);
        List<Article> collect = viewCountMap.entrySet()
                .stream()
                .map(entry ->
                        new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue())
                )
                .collect(Collectors.toList());
        //要执行的代码 更新到数据库
        articleService.updateBatchById(collect);
    }
}
