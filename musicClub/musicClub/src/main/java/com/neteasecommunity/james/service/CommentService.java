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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Component
public class CommentService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    //插入一条评论
    public Object pushComments(Comments comments) {
//        Integer parentId  = comments.getParentId();

        //在share表中,将id为parentId的share的commentCount加一,可以和点赞一起做成一个函数吗?
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(comments.getGmtCreate());
        comments.setLikeCount(0);
        return commentsMapper.insert(comments) == 1? ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }

    public List<CommentsDTO> getAllcommentsById(Integer id, Integer type) {
        System.out.println(id+"    "+type);
        CommentsExample commentsExample = new CommentsExample();
        commentsExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type);
        commentsExample.setOrderByClause("gmt_modified");
        List<Comments> comments = commentsMapper.selectByExampleWithBLOBs(commentsExample);
        if(comments.size() == 0){
            return null;
        }
        List<CommentsDTO> commentsDTOList = new ArrayList<>();
        CommentsDTO commentsDTO ;
        UserExample userExample ;
        List<User> users ;
        for(Comments cm : comments){
            commentsDTO = new CommentsDTO();
            userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(cm.getCommentator());
            users = userMapper.selectByExample(userExample);
            commentsDTO.setUser(users.get(0));
            BeanUtils.copyProperties(cm, commentsDTO);
            commentsDTOList.add(commentsDTO);
        }

        return commentsDTOList;
    }
}
