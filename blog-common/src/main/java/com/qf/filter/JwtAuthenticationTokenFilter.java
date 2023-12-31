package com.qf.filter;

import com.alibaba.fastjson2.JSON;
import com.qf.common.Constant;
import com.qf.domain.ResponseResult;
import com.qf.utils.JwtUtil;
import com.qf.utils.qfTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author : sin
 * @date : 2023/10/25 15:31
 * @Description :
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1、判断url是否匿名访问
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //匿名地址直接访问
        if(qfTools.contains(requestURI, Constant.annos)){
            filterChain.doFilter(request, response);
            return;
        }
        // 2、获取JWT
        String token = request.getHeader("Authorization");
        log.info("接收到的token:{}",token);
        if( token == null ) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(ResponseResult.error(400, "未登录无法访问！")));
            return;
        }
        if (token != null) {
            try {
                JwtUtil.tokenVerify(token);
            }catch (Exception e){
                response.setStatus(200);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(ResponseResult.error(400, "非法token")));
                return;
            }
        }
        //从redis中获取用户信息
        //String redisKey = "login:" + userid;
        //QfUser loginUser = redisCache.getCacheObject(redisKey);
        //if(Objects.isNull(loginUser)){
        //    throw new RuntimeException("用户未登录");
        //}
        //放行，让后面的过滤器执行
        filterChain.doFilter(request, response);
    }
}
