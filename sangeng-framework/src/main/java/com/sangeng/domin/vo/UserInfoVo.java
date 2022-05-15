package com.sangeng.domin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //这样就可以用链式访问，该注解设置为chain=true，生成setter方法返回this（也就是返回的是对象），代替了默认的返回void。
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}