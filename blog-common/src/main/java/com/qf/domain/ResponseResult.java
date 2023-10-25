package com.qf.domain;

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
public class ResponseResult<T> {

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

    /**
     * 自定义返回成功数据
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(200).setMessage("操作成功").setData(data);
    }

    /**
     * 自定义返回失败数据
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult fail(String message, T data) {
        return new ResponseResult().setCode(400).setMessage(message).setData(data);
    }

    public static String errorJSON(Object result) {
        return "错误: " + result;
    }
}
