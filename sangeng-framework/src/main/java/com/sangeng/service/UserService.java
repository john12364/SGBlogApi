package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.User;
import com.sangeng.domin.result.ResponseResult;

public interface UserService  extends IService<User> {
    ResponseResult userInfo();
}
