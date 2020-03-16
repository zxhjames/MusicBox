package com.neteasecommunity.james.dto;

import lombok.Data;

@Data
public class CountDTO {
    /**
     * 用来管理一个动态,或者是一条评论的点赞,评论数量
     */
    private Integer id;//动态或者评论的id
    private String username;//点赞人
    private Integer type;//行为 0->点赞  1->增加评论 2->转发
    private Integer status;
}
