//package com.qf.common.controller;
//
//import com.qf.common.domain.LoginUser;
//import com.qf.common.domain.ResponseResult;
//import com.qf.common.service.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author : sin
// * @date : 2023/10/25 12:25
// * @Description :
// */
//@RestController
//public class LoginController {
//    @Autowired
//    private LoginService loginService;
//
//    @PostMapping("/login")
//    public ResponseResult Login(@RequestBody LoginUser loginUser) {
//        return loginService.login(loginUser.getUsername(), loginUser.getPassword());
//    }
//}