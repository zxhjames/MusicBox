package com.neteasecommunity.james.dto;

import lombok.Data;

@Data
public class ActionsDTO {
    private String username;
    private String title;
    private String content;
    private Integer type; // type为0表示插入 为1表示更新
}
