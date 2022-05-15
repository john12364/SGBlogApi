package com.sangeng.domin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;
    //网站地址
    private String address;
    //描述
    private String description;
    //图片url
    private String logo;

    private String name;


}
