package com.neteasecommunity.james.dto;

import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.exception.CustomizeException;
import lombok.Data;

@Data
//错误处理工具类
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    //封装错误信息状态码
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO =  new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    //请求成功状态码,但不返回json数据
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }


    //请求成功携带状态码和json数组
    public static <T> ResultDTO okOf(T t){
      ResultDTO resultDTO= new ResultDTO();
      resultDTO.setCode(200);
      resultDTO.setMessage("请求成功");
      resultDTO.setData(t);
        return resultDTO;
    }
}
