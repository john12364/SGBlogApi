package com.sangeng.filter;


import com.alibaba.fastjson.JSON;
import com.sangeng.domin.entity.LoginUser;
import com.sangeng.domin.result.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.utils.JwtUtil;
import com.sangeng.utils.RedisCache;
import com.sangeng.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析token
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时  token非法
            //响应告诉前端需要重新登录
//            throw new RuntimeException("token非法");
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        String userid = claims.getSubject();

        //从redis中获取用户信息
        String redisKey = "bloglogin:"+userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)) {
            //说明登录过期  提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        //存入SecurityContextHolder
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
//                三个参数的存权限信息
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //同一个线程SecurityContextHolder只能拿到自己的数据，所以不用担心，不比对其进行清除
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
