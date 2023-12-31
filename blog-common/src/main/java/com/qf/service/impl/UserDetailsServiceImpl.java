package com.qf.service.impl;

import com.qf.domain.QfRole;
import com.qf.domain.QfUser;
import com.qf.mapper.QfUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

/**
 * @author : sin
 * @date : 2023/10/25 12:21
 * @Description :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private QfUserMapper qfUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、自定义数据库判断信息
        QfUser user = qfUserMapper.getByUsername(username);
        if (user == null) {
            throw new RuntimeException("账户不存在！");
        }
        // 2、将数据库中的角色拆分成SpringSecurity结构
        String roles = user.getRoles().stream().map(QfRole::getTag).collect(Collectors.joining(","));
        // 3、封装UserDetails返回
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(roles));

        return user;
    }
}