package com.qf.config;

import com.qf.common.Constant;
import com.qf.filter.JwtAuthenticationTokenFilter;
import com.qf.security.filter.LoginAuthenticationFilter;
import com.qf.security.handler.LoginFailureHandler;
import com.qf.security.handler.LoginSuccessHandler;
import com.qf.security.handler.NoAuthAccessDeniedHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;


/**
 * @author : sin
 * @date : 2023/10/25 14:32
 * @Description :
 */

//@EnableWebSecurity:开启SpringSecurity 之后会默认注册大量的过滤器servlet filter
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    NoAuthAccessDeniedHandler noAuthAccessDeniedHandler;

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    /**
     * token拦截器
     */
    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(e->e.requestMatchers(Constant.annos).permitAll());
        http.authorizeHttpRequests(e->e.requestMatchers(RegexRequestMatcher.regexMatcher("^\\S*[css|js]$")).permitAll());
        //http.authorizeHttpRequests(e->e.anyRequest().access(authorizationManager));

        //将JWT拦截器添加到UsernamePasswordAuthenticationFilter之前
        //登录之前验证token 从token中获取到登录凭证
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //重写登录
        http.addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin(e->e.successHandler(new LoginSuccessHandler()).failureHandler(new LoginFailureHandler()));
        http.exceptionHandling(e->e.accessDeniedHandler(noAuthAccessDeniedHandler));

        http.csrf(e -> e.disable());//跨域漏洞防御:关闭

        http.cors(e -> e.disable());//跨域拦截关闭

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        filter.setAuthenticationFailureHandler(new LoginFailureHandler());
        //LoginAuthenticationFilter 中需要使用到AuthenticationManager 不加会出现空指针
        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        return filter;
    }
}