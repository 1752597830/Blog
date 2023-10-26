package com.qf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : sin
 * @date : 2023/10/26 22:49
 * @Description :
 */

@RestController
public class MenuController {

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/check")
    public String check() {
        return "check";
    }
}