package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareExtMapper;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.ShareExample;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//注入bean
@Component
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private ShareExtMapper shareExtMapper;
    @Autowired
    private UserMapper userMapper;
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
        //后来在手机上测试又改为了longText
        shareExample.createCriteria().andCreatorEqualTo(username);
        return shareMapper.selectByExampleWithBLOBs(shareExample);
    }

    public List<ActionsDTO> getAllUserActions() {
        //首先获取所有动态
        List<Share> shares = shareExtMapper.selectAllByExampleWithDate(new ShareExample());
        //每次遍历一次单条动态,同时拷贝到ActionsDTO中,返回给前端
        List<ActionsDTO> actionsDTOList = new ArrayList<>();
        for(Share share : shares){
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(share.getCreator());
            List<User> user = userMapper.selectByExample(userExample);
            ActionsDTO actionsDTO = new ActionsDTO();
            actionsDTO.setUser(user.get(0));
            actionsDTO.setContent(share.getContent());
            actionsDTO.setTitle(share.getTitle());
            actionsDTO.setType(share.getType());
            actionsDTO.setGmtCreate(share.getGmtCreate());
            actionsDTO.setGmtModified(share.getGmtModified());
            actionsDTO.setCommentCount(share.getCommentCount());
            actionsDTO.setLikeCount(share.getLikeCount());
            actionsDTO.setViewCount(share.getViewCount());
            actionsDTOList.add(actionsDTO);
        }
        return actionsDTOList;
    }
}
