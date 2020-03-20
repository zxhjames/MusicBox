package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.RelationDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.mapper.RelationshipMapper;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value(("${props.redis.Table_User}"))
    private String Table_User;
    @Autowired
    private UserMapper userMapper;
    public Object addRelationShip(RelationDTO relationDTO) {
        /**
         * 放入缓存中
         */
        SetOperations<String,RelationDTO> set = redisTemplate.opsForSet();
        User u1 = userService.getUserInfo(relationDTO.getUname1());
        User u2 = userService.getUserInfo(relationDTO.getUname2());
        if(set.isMember("relationship",relationDTO)){
            set.remove("relationship",relationDTO);
            /**
             * 写缓存,需要定时写入数据库
             */
            u1.setConcerns(u1.getConcerns()-1);
            u2.setFollowers(u2.getFollowers()-1);
            userMapper.updateByPrimaryKey(u1);
            userMapper.updateByPrimaryKey(u2);
            return ResultDTO.okOf("delete");
        }else{
            set.add("relationship",relationDTO);
            /**
             * 写缓存
             */
            u1.setConcerns(u1.getConcerns()+1);
            u2.setFollowers(u2.getFollowers()+1);
            userMapper.updateByPrimaryKey(u1);
            userMapper.updateByPrimaryKey(u2);
            return ResultDTO.okOf("add");
        }

    }


    public Object judgeRelationShip(RelationDTO relationDTO){
        SetOperations<String,RelationDTO> set = redisTemplate.opsForSet();
        return set.isMember("relationship",relationDTO)?  ResultDTO.okOf("yes"):ResultDTO.okOf("no");
    }
}
