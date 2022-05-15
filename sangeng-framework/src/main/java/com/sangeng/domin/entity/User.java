package com.sangeng.domin.entity;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2022-05-13 21:31:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
//    Caused by: java.io.NotSerializableException:
//    Serializable是一个对象序列化的接口，一个类只有实现了Serializable接口，它的对象才是可序列化的。因此如果要序列化某些类的对象，这些类就必须实现Serializable接口。而实际上，Serializable是一个空接口，没有什么具体内容，它的目的只是简单的标识一个类的对象可以被序列化。
//            ————————————————
//    版权声明：本文为CSDN博主「斜阳雨陌」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_15037231/article/details/81913194
    //主键@TableId
    private Long id;

    //用户名
    @ApiModelProperty(name = "userName",value="用户名",required = true)
    private String userName;
    //昵称
    //用户名
    @ApiModelProperty(name = "nickName",value="用户名",required = false)
    private String nickName;
    //密码
    @ApiModelProperty(name = "nickName",value="用户名",required = true)
    private String password;
    //用户类型：0代表普通用户，1代表管理员
    private String type;
    //账号状态（0正常 1停用）
    private String status;
    //邮箱
    private String email;
    //手机号
    private String phonenumber;
    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;
    //创建人的用户id
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}

