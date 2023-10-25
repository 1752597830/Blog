package com.qf.service;

import com.qf.domain.ResponseResult;

public interface LoginService {

    ResponseResult login(String username, String password);

}
