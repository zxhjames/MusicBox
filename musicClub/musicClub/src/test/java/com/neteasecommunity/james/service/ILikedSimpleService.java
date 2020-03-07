package com.neteasecommunity.james.service;

import com.neteasecommunity.james.model.BType;
//点赞操作接口
public interface ILikedSimpleService {
    /**
     * 点赞/取消点赞
     * @param bType 业务类型
     * @param subjectId 被点赞主体Id
     * @param postId 点赞主体Id
     */

    void Liked(BType bType,Object subjectId,Object postId);

    /**
     * 获取点赞数量
     *
     * @param bType 业务类型
     * @param subjectId 被点赞主体Id
     * @return 点赞数量
     */
    Long count(BType bType,Object subjectId);
}
