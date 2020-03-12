package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.CommentsDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.CommentsMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.Comments;
import com.neteasecommunity.james.model.CommentsExample;
import com.neteasecommunity.james.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Transactional
@Component
public class CommentService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value(("${props.redis.Table_Comment}"))
    private String Table_Comment;
    @Autowired
    private UserService userService;

    /**
     * 插入一条评论的功能,这个评论可能是一级评论,也可能是二级评论
     * @param comments
     * @return
     */
    public Object pushComments(Comments comments) {
        //在share表中,将id为parentId的share的commentCount加一,可以和点赞一起做成一个函数吗?
        comments.setGmtCreate(System.currentTimeMillis());
        comments.setGmtModified(comments.getGmtCreate());
        comments.setLikeCount(0);
        int status = commentsMapper.insert(comments);
        return status == 1? ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
    }


    /**
     * 根据评论的id和type获取相应的评论
     * @param id
     * @param type
     * @return
     */
    public List<CommentsDTO> getAllcommentsById(Integer id, Integer type) {
            CommentsExample commentsExample = new CommentsExample();
            commentsExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type);
            List<Comments> comments = commentsMapper.selectByExampleWithBLOBs(commentsExample);
            if(comments == null || comments.size()==0){
                return null;
            }
            List<CommentsDTO> commentsDTOList = new ArrayList<>();
            CommentsDTO commentsDTO ;
            User user ;
            for(Comments cm : comments){
                commentsDTO = new CommentsDTO();
                user = userService.getUserInfo(cm.getCommentator());
                commentsDTO.setUser(user);
                BeanUtils.copyProperties(cm, commentsDTO);
                commentsDTOList.add(commentsDTO);
            }
            /**
             * 再次插入到缓存片中
             */
            commentsDTOList.stream().sorted(Comparator.comparing(CommentsDTO::getGmtModified).reversed());
            System.out.println("插入数据库中评论");
            return commentsDTOList;
        }

    }

