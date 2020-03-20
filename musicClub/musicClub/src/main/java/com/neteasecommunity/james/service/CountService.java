package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.CountDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.model.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用来处理点赞,评论,转发数控制的Dao
 */
@Component
public class CountService {
    @Value(("${props.redis.Table_Share}"))
    private String redis_Share;
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 处理点赞
     * @param countDTO
     * @return
     */
    @Transactional
    public ResultDTO operateCount(CountDTO countDTO) {
        String username = countDTO.getUsername();
        Integer id = countDTO.getId();
        Integer type = countDTO.getType();
        Integer status = countDTO.getStatus();
        Share share = shareMapper.selectByPrimaryKey(id);
        if (status == 0) {
            //处理动态点赞
            if (type == 0) {
                share.setLikeCount(share.getLikeCount() + 1);
            } else if(type == 1) {
                share.setCommentCount(share.getCommentCount() + 1);
            } else if (type == 2) {
                share.setRepostCount(share.getRepostCount() + 1);
            }
            int a = shareMapper.updateByPrimaryKey(share);
            return a == 1 ? ResultDTO.okOf() : ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
        }
        return null;
    }
}
