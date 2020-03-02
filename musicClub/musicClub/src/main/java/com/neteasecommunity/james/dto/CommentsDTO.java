package com.neteasecommunity.james.dto;

import com.neteasecommunity.james.model.User;
import lombok.Data;

@Data
public class CommentsDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Long gmtCreate;
    private Long gmtModified;
    private String commentator;
    private String content;
    private Integer likeCount;
    private User user;
}
