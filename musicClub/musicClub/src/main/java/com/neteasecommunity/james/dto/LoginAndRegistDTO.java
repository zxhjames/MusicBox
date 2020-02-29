package com.neteasecommunity.james.dto;

import lombok.Data;

@Data
public class LoginAndRegistDTO {
    private String username;
    private String password;
    private String avatar_url;
    private String token;
    private Integer rank;
    private Integer concerns;
    private Integer followers;
}
