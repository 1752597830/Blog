package com.qf.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.domain.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : sin
 * @date : 2023/10/25 21:49
 * @Description :
 */
@Slf4j
@Component
public class NoAuthAccessDeniedHandler implements AccessDeniedHandler {

    @SneakyThrows
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)  {
        log.info("认证未通过:{}",accessDeniedException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(500);
        response.getWriter().write(ResponseResult.errorJSON(accessDeniedException.getMessage()));
    }
}