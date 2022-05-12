package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.Category;
import com.sangeng.domin.result.ResponseResult;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-05-12 10:23:21
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
