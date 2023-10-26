package com.qf.security.auth;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.common.Constant;
import com.qf.domain.*;
import com.qf.mapper.QfMenuMapper;
import com.qf.mapper.QfUserMapper;
import com.qf.utils.qfTools;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author : sin
 * @date : 2023/10/25 21:40
 * @Description : 权限验证
 */

@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Resource
    QfMenuMapper menuMapper;

    @Resource
    QfUserMapper userMapper;
    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        AuthorizationManager.super.verify(authentication, requestAuthorizationContext);
    }
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        if(!(authentication.get().getPrincipal() instanceof QfUser)){
            throw new AccessDeniedException("匿名不可访问!");
        }
        QfUser user = (QfUser) authentication.get().getPrincipal();

        user = userMapper.getByUsername(user.getUsername());

        //访问的接口地址
        String requestURI = requestAuthorizationContext.getRequest().getRequestURI();

        //匿名地址直接访问
        if(qfTools.contains(requestURI, Constant.annos)){
            return new AuthorizationDecision(true);
        }
        //查询当前请求的接口需要哪些权限能访问
        QueryWrapper<QfMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("m.path",requestURI);
        List<List<QfMenu>> menusByRoleIdList = new ArrayList<>();
        for (QfRole role: user.getRoles()) {
            // 用roleid获取menuid
            List<QfMenu> menu = menuMapper.selectByroleId(role.getId());
            menusByRoleIdList.add(menu);
        }
        // 用路径来获取菜单
        List<QfMenu> menus = menuMapper.listMenu(queryWrapper);
        // 遍历菜单判断权限
        for (QfMenu menu : menus) {
            for (List<QfMenu> qfMenu : menusByRoleIdList) {
                for (QfMenu menu1 : qfMenu) {
                    if(menu.getId().equals(menu1.getId())) {
                        return new AuthorizationDecision(true);
                    }
                }
            }
        }
        throw new AccessDeniedException(user.getUsername()+",没有访问:"+requestURI+"的权限");
    }
}