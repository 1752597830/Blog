package com.qf.security.handler;

import com.alibaba.fastjson2.JSON;
import com.qf.domain.QfMenu;
import com.qf.domain.QfUser;
import com.qf.domain.ResponseResult;
import com.qf.utils.BeanUtils;
import com.qf.utils.JwtUtil;
import com.qf.utils.RouteTools;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.qf.domain.QfRole;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
        // 生成token
        String token = JwtUtil.token(auth);
        // 通过Bean方式注入
        RouteTools routeTools = BeanUtils.getBean("routeTools");
        // 获取目录菜单权限
        List<QfMenu> routes = routeTools.buildRouteTree(user.getId());
        // 获取按钮权限
        List<String> Permissions = routeTools.getPermissions(user.getId());
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("username",user.getUsername());
        data.put("roles",user.getRoles().stream().map(QfRole::getTag).collect(Collectors.toList()));
        data.put("userPermissions",Permissions);
        data.put("routes",routes);
        data.put("accessToken",token);
        data.put("refreshToken",token);

        log.info("登录成功 {}",user.getUsername());



        response.getWriter().write(ResponseResult.successJSON(data));
    }
    //{
    //    path: '',
    //    component: '',
    //    name: '',
    //    meta: {
    //        Auth: true,
    //        buttonPermissions: []
    //    }
    //}
}

