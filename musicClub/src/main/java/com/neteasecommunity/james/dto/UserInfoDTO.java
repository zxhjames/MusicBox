package com.neteasecommunity.james.dto;

import com.neteasecommunity.james.model.User;
import lombok.Data;

@Data
public class UserInfoDTO {
    private User user;
    private Integer id;

    private String username;

    private String password;

    private Long gmtCreate;

    private Long gmtModified;

    private String avatarUrl;


    private Integer rank;


    private Integer concerns;

    private Integer followers;

}
