package com.neteasecommunity.james.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.ShareExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//注入bean
@Component
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;

    /**
     * 插入一条新的动态
     *
     * @param actionsDTO
     * @return ResultDTO
     */
    public Object insertActions(ActionsDTO actionsDTO) {
        Share share = new Share();
        share.setCreator(actionsDTO.getUsername());
        share.setTitle(actionsDTO.getTitle());
        share.setContent(actionsDTO.getContent());
        share.setGmtCreate(System.currentTimeMillis());
        share.setGmtModified(share.getGmtCreate());
        share.setViewCount(0);
        share.setCommentCount(0);
        share.setLikeCount(0);
        share.setType(0);
        return shareMapper.insert(share) == 1 ? ResultDTO.okOf("发布成功") : ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    public List<Share> getUserActionsByName(String username) {
        ShareExample shareExample = new ShareExample();
        //这里如果数据库的content要使用blob字段
        shareExample.createCriteria().andCreatorEqualTo(username);
        return shareMapper.selectByExampleWithBLOBs(shareExample);
    }
}
