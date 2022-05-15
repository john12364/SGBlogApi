package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.Link;
import com.sangeng.domin.result.ResponseResult;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-05-13 20:56:47
 */
public interface LinkService extends IService<Link> {

    //在友链页面要查询出所有的审核通过的友链。
    ResponseResult getAllLink();
}
