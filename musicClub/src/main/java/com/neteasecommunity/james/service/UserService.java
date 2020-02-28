package com.neteasecommunity.james.service;

import com.neteasecommunity.james.dto.LoginAndRegistDTO;
import com.neteasecommunity.james.dto.ResultDTO;
import com.neteasecommunity.james.exception.CustomizeErrorCode;
import com.neteasecommunity.james.mapper.UserMapper;
import com.neteasecommunity.james.model.User;
import com.neteasecommunity.james.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //用户注册
    public ResultDTO UserRegister(User user){
        return userMapper.insert(user) == 1?ResultDTO.okOf():ResultDTO.errorOf(CustomizeErrorCode.EXIST_USER);
    }



    //用户登录
    //使用redis除了问题
    public ResultDTO UserLogin(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        //比对密码
        List<User> dbUsers = userMapper.selectByExample(userExample);
        if(dbUsers.size() == 0){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_USER);
        }
        if(dbUsers.get(0).getPassword().equals(user.getPassword())){
            String token = UUID.randomUUID().toString();
            //设置token在缓存中的过期时间
            redisTemplate.opsForValue().set(user.getUsername(),token);
            LoginAndRegistDTO u = new LoginAndRegistDTO();
            u.setAvatar_url(dbUsers.get(0).getAvatarUrl());
            u.setRank(dbUsers.get(0).getRank());
            u.setToken(token);
            return ResultDTO.okOf(u);
        }
        return ResultDTO.errorOf(CustomizeErrorCode.ERROR_PWD);
    }

}
