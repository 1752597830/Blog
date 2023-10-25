//package com.qf.controller;
//
//import com.qf.domain.ResponseResult;
//import com.qf.service.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author : sin
// * @date : 2023/10/25 16:07
// * @Description :
// */
//@RestController
//public class LoginController {
//    @Autowired
//    private LoginService loginService;
//
//    //@PostMapping("/login")
//    //public ResponseResult Login(@RequestBody LoginUser loginUser) {
//    //    System.out.println(loginUser);
//    //    return loginService.login(loginUser.getUsername(), loginUser.getPassword());
//    //}
//
//    @GetMapping("/check")
//    public ResponseResult check() {
//        return new ResponseResult<>().setMessage("校验成功").setCode(200);
//    }
//
//    @GetMapping("/test")
//    public ResponseResult test() {
//        return new ResponseResult<>().setMessage("test").setCode(200);
//    }
//}