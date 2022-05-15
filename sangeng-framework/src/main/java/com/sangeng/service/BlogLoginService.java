package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.User;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.domin.vo.LoginFormVo;


/**
 * 用户表(User)表服务接口
 *userService
 * @author makejava
 * @since 2022-05-13 21:31:25
 */
public interface BlogLoginService extends IService<User> {


    ResponseResult login(LoginFormVo user);

    ResponseResult logout();
}
