package com.qf.security.handler;

import com.alibaba.fastjson2.JSON;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.qf.domain.QfMenu;
import com.qf.domain.QfUser;
import com.qf.utils.BeanUtils;
import com.qf.utils.JwtUtil;
import com.qf.utils.RouteTools;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // 获取菜单权限
        RouteTools routeTools = BeanUtils.getBean("routeTools");
        List<QfMenu> routes = routeTools.buildRouteTree(user.getId());

        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> data = new HashMap<>();
        //data.put("username",user.getUsername());
        //data.put("roles",user.getRoles().stream().map(QfRole::getTag).collect(Collectors.toList()));
        data.put("routes",routes);
        //data.put("accessToken",token);
        //data.put("refreshToken",token);

        log.info("登录成功 {}",user.getUsername());
        //Bean1 bean1 = new Bean1();
        //bean1.setCODE("00000");
        //bean1.setMSG("成功!");
        //bean1.setMESSAGE("成功!");
        //bean1.setData("1");
        //bean1.setResult("1");
        //
        //String jsonString = JSON.toJSONString(bean1);
        //JSONObject jsonobject0 = JSONObject.parseObject(jsonString);
        //JSONObject jsonobject = JSONObject.parseObject(jsonString, Feature.OrderedField);

        String a = JSON.toJSONString(routes);

        response.getWriter().write(a);
    }

}