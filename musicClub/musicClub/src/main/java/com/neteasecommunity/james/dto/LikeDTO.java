package com.neteasecommunity.james.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private Integer repostCount;
}
