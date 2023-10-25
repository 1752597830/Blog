package com.qf.security.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.common.Constant;
import com.qf.domain.Menu;
import com.qf.domain.User;
import com.qf.domain.Perm;
import com.qf.mapper.MenuMapper;
import com.qf.mapper.UserMapper;
import com.qf.utils.qfTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author : sin
 * @date : 2023/10/25 21:40
 * @Description : 权限验证
 */

@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    UserMapper userMapper;
    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        AuthorizationManager.super.verify(authentication, requestAuthorizationContext);
    }
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        if(!(authentication.get().getPrincipal() instanceof User)){
            throw new AccessDeniedException("匿名不可访问!");
        }
        User user = (User) authentication.get().getPrincipal();

        user = userMapper.getByUsername(user.getUsername());

        //当前登录人的权限
        List<String> auths = user.getPerms().stream().map(Perm::getTag).collect(Collectors.toList());

        //访问的接口地址
        String requestURI = requestAuthorizationContext.getRequest().getRequestURI();

        //匿名地址直接访问
        if(qfTools.contains(requestURI, Constant.annos)){
            return new AuthorizationDecision(true);
        }

        //查询当前请求的接口需要哪些权限能访问
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("m.type",2);
        queryWrapper.eq("m.path",requestURI);
        List<Menu> menus = menuMapper.listMenu(queryWrapper);
        System.out.println(menus);
        for (String auth : auths) {
            for (Menu menu : menus) {
                List<String> menuPerms = menu.getPerms().stream().map(Perm::getTag).collect(Collectors.toList());
                if(menuPerms.contains(auth)){
                    return new AuthorizationDecision(true);
                }
            }
        }

        throw new AccessDeniedException(user.getUsername()+",没有访问:"+requestURI+"的权限");
    }
}