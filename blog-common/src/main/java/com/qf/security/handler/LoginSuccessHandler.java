package com.qf.security.handler;

import com.qf.domain.QfUser;
import com.qf.domain.QfRole;
import com.qf.domain.ResponseResult;
import com.qf.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : sin
 * @date : 2023/10/25 21:46
 * @Description :
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException {

        QfUser user = (QfUser) auth.getPrincipal();
        //
        String token = JwtUtil.token(auth);
        //response.addHeader("token", token);
        //
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("username",user.getUsername());
        data.put("roles",user.getRoles().stream().map(QfRole::getTag).collect(Collectors.toList()));
        data.put("accessToken",token);
        data.put("refreshToken",token);

        log.info("登录成功 {}",user.getUsername());

        response.getWriter().write(ResponseResult.successJSON(data));
    }

}