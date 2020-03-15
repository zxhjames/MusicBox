package com.neteasecommunity.james.dto;

import lombok.Data;

@Data
//用户注册登录工具类
public class LoginAndRegistDTO {
    private Integer id;
    private String username;
    private String password;
    private String avatarUrl;
    private String token;
    private Integer rank;
    private Integer concerns;
    private Integer followers;
    private Long gmtCreate;
    private Long gmtModified;
}
