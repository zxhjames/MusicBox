package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.CommentsMapper;
import com.neteasecommunity.james.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {
    @Autowired
    private CommentsMapper commentsMapper;

    //插入一条评论
    public Object pushComments(Comments comments) {
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(comments.getGmtCreate());
        comments.setLikeCount(0);
        return commentsMapper.insert(comments) == 1? ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    public Object getAllcommentsById(Integer id, Integer type) {
        return ResultDTO.okOf(id);
    }
}
