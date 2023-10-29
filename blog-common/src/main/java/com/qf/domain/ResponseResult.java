package com.qf.domain;

import com.alibaba.fastjson2.JSON;
import com.qf.common.ResultCode;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : sin
 * @date : 2023/10/25 14:18
 * @Description :
 */
@Data
// 设置链式数据
@ToString
@Accessors(chain = true)
public class ResponseResult <T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static ResponseResult error(int code, String msg) {
        return new ResponseResult().setCode(code).setMessage(msg);
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult().setCode(ResultCode.ERROR.getCode()).setMessage(msg);
    }

    public static String errorJSON(String result) {
        return JSON.toJSONString(result);
    }

    public static <T> String successJSON(T data) {
        return JSON.toJSONString(new ResponseResult().setCode(ResultCode.SUCCESS.getCode()).setMessage(ResultCode.SUCCESS.getMsg()).setData(data));
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString("错误"));
        System.out.println(errorJSON("错误"));
    }

}
