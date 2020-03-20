package com.neteasecommunity.james.dto;

import com.neteasecommunity.james.model.User;
import lombok.Data;

@Data
//用户上传动态获取动态的工具类
public class ActionsDTO {
    private User user;
    private Integer id;
    private Integer parentId;
    private String creator;
    private String title;
    private String content;
    private Integer type;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Integer repostCount;
}
