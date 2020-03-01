package com.neteasecommunity.james.dto;

import lombok.Data;

@Data
public class CommentsDTO {
    private Integer id;
    private Integer parent_id;
    private Integer type;
    private Long gmtCreate;
    private Long gmtModified;
    private String commentator;
    private String content;
    private Integer likeCount;
}
