package com.sangeng.controller;

import com.sangeng.domin.result.ResponseResult;
import com.sangeng.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@Api(tags = "上传管理")
@RequestMapping
public class UploadController {
    @Autowired
    private UploadService uploadService;
//    @PostMapping(value = "/upload",consumes = "multipart/form-data",headers = "content-type=multipart/form-data")
//记录一下：springboot整合swagger测试上传文件失效 需要在MultipartFile @RequestPart   参考地址：https://wenku.baidu.com/view/eae5bbd8f405cc1755270722192e453610665b0a.html
    @ApiOperation(value = "头像上传")
    @PostMapping(value = "/upload",consumes = "multipart/form-data",headers = "content-type=multipart/form-data")
    public ResponseResult uploadImg(@RequestPart MultipartFile img){
        return uploadService.uploadImg(img);
    }
//        @ApiImplicitParam(name = "img",value = "用户上传的文件",required = true,dataType = "content-type=multipart/form-data")
}