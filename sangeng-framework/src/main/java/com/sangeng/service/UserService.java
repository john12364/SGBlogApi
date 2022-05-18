package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domin.entity.User;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.domin.vo.User.UserInfoVo;
import com.sangeng.domin.vo.User.UserRegisterVo;

public interface UserService  extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(UserInfoVo userInfoVo);

    ResponseResult register(UserRegisterVo userRegisterVo);
}
