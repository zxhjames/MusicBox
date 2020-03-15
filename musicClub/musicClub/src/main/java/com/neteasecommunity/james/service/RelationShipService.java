package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.mapper.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

@Component
public class RelationShipService {
    @Autowired
    private RelationshipMapper relationshipMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Transactional
//    public Object addRelationShip(Relationship relationship) {
//        RelationshipExample relationshipExample = new RelationshipExample();
//
//        //同时增加
//        User u1 = userMapper.selectByPrimaryKey(relationship.getUid1());
//        User u2 = userMapper.selectByPrimaryKey(relationship.getUid2());
//        u1.setConcerns(u1.getConcerns()+1);
//        u2.setFollowers(u2.getFollowers()+1);
//        int b = userMapper.updateByPrimaryKey(u1);
//        int c = userMapper.updateByPrimaryKey(u2);
//        RelationDTO relationDTO = new RelationDTO();
//        relationDTO.setUname1(u1.getUsername());
//        relationDTO.setUname2(u2.getUsername());
//        SetOperations<String,RelationDTO> set = redisTemplate.opsForSet();
//        set.add("relationShip",relationDTO);
//        return (b==1&&c==1)? ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.SERVER_ERROR);
//
//    }

    public Object addRelationShip(RelationDTO relationDTO) {
        /**
         * 放入缓存中
         */
        SetOperations<String,RelationDTO> set = redisTemplate.opsForSet();
        if(set.isMember("relationship",relationDTO)){
            set.remove("relationship",relationDTO);
            System.out.println("取消关注");
            return ResultDTO.okOf("delete");
        }else{
            set.add("relationship",relationDTO);
            System.out.println("添加关注");
            return ResultDTO.okOf("add");
        }

    }


    public Object judgeRelationShip(RelationDTO relationDTO){
        SetOperations<String,RelationDTO> set = redisTemplate.opsForSet();
        if(set.isMember("relationship",relationDTO)){
            System.out.println("有这个关系");
            return ResultDTO.okOf("yes");
        }else{
            System.out.println("wu这个关系");
            return ResultDTO.okOf("no");
        }
    }
}
