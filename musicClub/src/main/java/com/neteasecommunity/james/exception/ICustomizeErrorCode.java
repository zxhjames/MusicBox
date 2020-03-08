package com.neteasecommunity.james.exception;

//后端错误码的接口
public interface ICustomizeErrorCode {
    String getMessage();
    Integer getCode();
}
