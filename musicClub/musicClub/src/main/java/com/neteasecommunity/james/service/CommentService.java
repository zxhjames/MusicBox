package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.CommentsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.CommentsMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.model.CommentsExample;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    //插入一条评论
    public Object pushComments(Comments comments) {
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(comments.getGmtCreate());
        comments.setLikeCount(0);
        return commentsMapper.insert(comments) == 1? ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    public List<CommentsDTO> getAllcommentsById(Integer id, Integer type) {
//        if(type == 1){
//            System.out.println(id+"....."+type);
//        }
        CommentsExample commentsExample = new CommentsExample();
        //做一次判断

        commentsExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type);
        commentsExample.setOrderByClause("gmt_modified");
        List<Comments> comments = commentsMapper.selectByExampleWithBLOBs(commentsExample);
        if(comments.size() == 0){
            return null;
        }
        List<CommentsDTO> commentsDTOList = new ArrayList<>();
        for(Comments cm : comments){
            CommentsDTO commentsDTO = new CommentsDTO();
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(cm.getCommentator());
            List<User> users = userMapper.selectByExample(userExample);
            String realUrl = "http://localhost:8081/img/user/" + users.get(0).getAvatarUrl();
            users.get(0).setAvatarUrl(realUrl)  ;
            commentsDTO.setUser(users.get(0));

            BeanUtils.copyProperties(cm, commentsDTO);
            commentsDTOList.add(commentsDTO);
        }

        return commentsDTOList;
    }
}
