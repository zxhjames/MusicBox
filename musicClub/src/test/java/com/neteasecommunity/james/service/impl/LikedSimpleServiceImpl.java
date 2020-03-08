package com.neteasecommunity.james.service.impl;

import com.neteasecommunity.james.model.BType;
import com.neteasecommunity.james.service.ILikedSimpleService;
import com.neteasecommunity.james.util.LikedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class LikedSimpleServiceImpl implements ILikedSimpleService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 点赞/取消点赞
     * @param bType 业务类型
     * @param subjectId 被点赞主体Id
     * @param postId 点赞主体Id
     */
    @Override
    public void Liked(BType bType, Object subjectId, Object postId) {
        //1.生产key
        String key = LikedUtil.getKey(bType,subjectId);
        boolean liked = stringRedisTemplate.opsForSet().isMember(key,postId);
        if(liked){
            //取消赞
            stringRedisTemplate.opsForSet().remove(key,postId);
        }else{
            //点赞
            stringRedisTemplate.opsForSet().add(key,postId+"");
        }
    }

    /**
     * 获取点赞数量
     *
     * @param bType 业务类型
     * @param subjectId 被点赞主体Id
     * @return 点赞数量
     */
    @Override
    public Long count(BType bType, Object subjectId) {
        return null;
    }
}
