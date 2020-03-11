package com.neteasecommunity.james.dto;

import com.neteasecommunity.james.model.User;
import lombok.Data;

@Data
//用户上传动态获取动态的工具类
public class ActionsDTO {
//    private User user; //上传动态的用户个人信息
//    private Integer id;
//    private Integer parentId;
//    private String creator;//上传者
//    private String title; //动态标题
//    private String content; //动态内容
//    private Integer type; //动态类型 type为0表示插入 为1表示更新
//    private Long gmtCreate; //创建时间
//    private Long gmtModified; //最近修改时间
//    private Integer commentCount;//评论数
//    private Integer viewCount;//浏览数
//    private Integer likeCount;//点赞数
//    private Integer repostCount;//转发数
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
