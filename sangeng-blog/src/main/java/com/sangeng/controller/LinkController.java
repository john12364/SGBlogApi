package com.sangeng.controller;

import com.sangeng.domin.result.ResponseResult;
import com.sangeng.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "外链管理")
@RestController
@RequestMapping("/link")
public class LinkController {


    @Autowired
    private LinkService linkService;

    @ApiOperation(value = "获取所有审核通过的外链")
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
