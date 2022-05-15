package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domin.entity.User;
import org.springframework.stereotype.Repository;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-13 21:31:23
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
