package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.CountDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 用来处理点赞,评论,转发数控制的Dao
 */
@Component
public class CountService {
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    private String ActionCountTable = "ActionCountTable";
    private String CommentCountTable = "CommentCountTable";

    /**
     * 处理点赞
     * @param countDTO
     * @return
     */
    public ResultDTO operateCount(CountDTO countDTO) {
        String username = countDTO.getCommentator();
        Integer id = countDTO.getId();
        Integer type = countDTO.getType();
        Integer status = countDTO.getStatus();
        if (status == 0) {
            //处理动态点赞
            Share share = shareMapper.selectByPrimaryKey(id);
            if (type == 0) {
                share.setLikeCount(share.getLikeCount() + 1);
            } else if(type == 1) {
                share.setCommentCount(share.getCommentCount() + 1);
            } else if (type == 2) {
                share.setRepostCount(share.getRepostCount() + 1);
            }
            int a = shareMapper.updateByPrimaryKey(share);
            return a == 1 ? ResultDTO.okOf() : ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
        } else if (status == 1) {
            //处理评论点赞


        }
        return null;
    }
}
