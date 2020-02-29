package com.neteasecommunity.james.exception;

public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
