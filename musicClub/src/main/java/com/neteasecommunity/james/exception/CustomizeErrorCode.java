package com.neteasecommunity.james.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    ERROR_PWD(2003,"用户密码错误")
    ,
    EXIST_USER(2004,"用户名已存在,请你换个用户名"),
    NO_USER(2005,"用户不存在,请先注册"),
    FILE_ERROR(2006,"文件异常");
    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code,String message){
        this.message = message;
        this.code =  code;
    }


}
