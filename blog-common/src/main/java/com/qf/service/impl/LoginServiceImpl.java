//package com.qf.service.impl;
//
//import com.qf.domain.ResponseResult;
//import com.qf.service.LoginService;
//import com.qf.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author : sin
// * @date : 2023/10/25 14:24
// * @Description :
// */
//@Service
//public class LoginServiceImpl implements LoginService {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Override
//    public ResponseResult login(String username, String password) {
//        // 1、登录前置校验
//
//        // 2、验证码校验
//
//        // 3、使用ProviderManager auth进行校验
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        // 4、调用UserDetailsService.loadUserByUsername
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        // 4自己生成jwt token给前端
//        String token = JwtUtil.generateToken(username);
//        Map<String, Object> map = new HashMap();
//        map.put("token", token);
//        return new ResponseResult<>().setCode(200).setData(map).setMessage("登录成功");
//    }
//}