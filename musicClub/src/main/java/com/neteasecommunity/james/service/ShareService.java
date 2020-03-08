package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.ActionsDTO;
import com.neteasecommunity.james.dto.CommentsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.ShareExtMapper;
import com.neteasecommunity.james.mapper.ShareMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Value(("${prop.avatarUrl}"))
    private String AvatarUrl;
    /**
     * 插入一条新的动态
     *
     * @param actionsDTO
     * @return ResultDTO
     */
    public Object insertActions(ActionsDTO actionsDTO) {
        Share share = new Share();
        System.out.println(actionsDTO.getCreator());
        share.setCreator(actionsDTO.getCreator());
        share.setTitle(actionsDTO.getTitle());
        share.setContent(actionsDTO.getContent());
        share.setGmtCreate(System.currentTimeMillis());
        share.setGmtModified(share.getGmtCreate());
        share.setViewCount(0);
        share.setCommentCount(0);
        share.setLikeCount(0);
        share.setType(actionsDTO.getType());
        return shareMapper.insert(share) == 1 ? ResultDTO.okOf("发布成功") : ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    public List<Share> getUserActionsByName(String username) {
        ShareExample shareExample = new ShareExample();
        //这里如果数据库的content要使用blob字段
        //后来在手机上测试又改为了longText
        //根据时间降序排序
        shareExample.setOrderByClause("gmt_modified");
        shareExample.createCriteria().andCreatorEqualTo(username);
        return shareMapper.selectByExampleWithBLOBs(shareExample);
    }

    public List<ActionsDTO> getAllUserActions() {
        //首先获取所有动态
        ShareExample shareExample = new ShareExample();
        shareExample.setOrderByClause("gmt_modified");
        List<Share> shares = shareExtMapper.selectAllByExampleWithDate(shareExample);
        //每次遍历一次单条动态,同时拷贝到ActionsDTO中,返回给前端
        List<ActionsDTO> actionsDTOList = new ArrayList<>();
        List<User> user = null;
        ActionsDTO actionsDTO = null;
        for(Share share : shares){
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(share.getCreator());
            user = userMapper.selectByExample(userExample);
            actionsDTO = new ActionsDTO();
            actionsDTO.setUser(user.get(0));
            //原来是我字段复制不完整啊
            actionsDTO.setId(share.getId());
            actionsDTO.setCreator(share.getCreator());
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

    public Object deleteOneActionByActionId(Integer id) {
        return shareMapper.deleteByPrimaryKey(id) == 1?ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }


}
