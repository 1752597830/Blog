package com.qf.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResultCode {

    SUCCESS(200, "成功"),

    ERROR(500, "失败"),

    USERNAME_NOT_FOUND(1002,"用户名未找到");

    private int code;

    private String msg;

}
