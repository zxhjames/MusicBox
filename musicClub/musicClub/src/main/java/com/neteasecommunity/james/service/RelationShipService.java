package com.neteasecommunity.james.service;

import com.alibaba.fastjson.JSON;
import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.mapper.RelationshipMapper;
import com.neteasecommunity.james.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

@Component
public class RelationShipService {
    @Autowired
    private RelationshipMapper relationshipMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value(("${props.redis.Table_User}"))
    private String Table_User;
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
        System.out.println(relationDTO.getUname1()+" "+relationDTO.getUname2());
        SetOperations<String,RelationDTO> set = redisTemplate.opsForSet();
        HashOperations<String,String, User> map= redisTemplate.opsForHash();
        User u1 = JSON.parseObject(JSON.toJSONString(map.get(Table_User,relationDTO.getUname1())),User.class);
        User u2 =JSON.parseObject(JSON.toJSONString(map.get(Table_User,relationDTO.getUname2())),User.class);
        if(set.isMember("relationship",relationDTO)){
            set.remove("relationship",relationDTO);
            System.out.println("取消关注");
            /**
             * 写缓存,需要定时写入数据库
             */

            u1.setConcerns(u1.getConcerns()-1);
            u2.setFollowers(u2.getFollowers()-1);
            map.put(Table_User,u1.getUsername(),u1);
            map.put(Table_User,u2.getUsername(),u2);
            return ResultDTO.okOf("delete");
        }else{
            set.add("relationship",relationDTO);
            System.out.println("添加关注");
            /**
             * 写缓存
             */
            u1.setConcerns(u1.getConcerns()+1);
            u2.setFollowers(u2.getFollowers()+1);
            map.put(Table_User,u1.getUsername(),u1);
            map.put(Table_User,u2.getUsername(),u2);
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
