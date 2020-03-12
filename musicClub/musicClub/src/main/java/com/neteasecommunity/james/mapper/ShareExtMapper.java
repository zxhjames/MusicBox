package com.neteasecommunity.james.mapper;

import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.ShareExample;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ShareExtMapper {
    //根据用户名查询所有动态,根据发布时间降序排列
    List<Share> selectByExampleWithDate(ShareExample shareExample);
    //查询出所有的动态,根据发布时间降序排列
    List<Share> selectAllByExampleWithDate(ShareExample shareExample);
    //处理点赞的行为
    int updateLikeCount(Integer id,Integer type,Integer action);
}
