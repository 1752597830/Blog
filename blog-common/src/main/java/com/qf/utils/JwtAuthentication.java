package com.qf.utils;


import com.qf.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
/**
 * @author : sin
 * @date : 2023/10/25 23:07
 * @Description :
 * {@link Authentication}及其实现类的大部分属性没有提供setter方法，
 * 所以在通过json转换回Authentication时，没有setter方法的属性就赋值为空，此类是json转换回Authentication的中间类。
 */
public class JwtAuthentication implements Authentication {

    private Collection<SimpleGrantedAuthority> authorities;
    private Object details;
    private boolean authenticated;
    private User principal;
    private Object credentials;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    @Override
    public String getName() {
        return null;
    }
}