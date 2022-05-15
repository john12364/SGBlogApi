package com.sangeng.handler;

import com.alibaba.fastjson.JSON;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//授权失败处理器
// 不加@compoent注解报错 ：Consider defining a bean of type 'org.springframework.security.web.access.AccessDeniedHandler' in your configuration.
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        //无权限操作
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}
